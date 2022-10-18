package tech.petrych.congestion.calculator.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.petrych.congestion.calculator.errorhandling.InputModelValidationException;
import tech.petrych.congestion.calculator.model.IVehicle;
import tech.petrych.congestion.calculator.service.ICalculatorService;
import tech.petrych.congestion.calculator.service.IVehicleService;

import java.time.LocalDateTime;
import java.util.Objects;

import static tech.petrych.congestion.calculator.service.DateUtil.sortDates;

@RestController
public class CongestionTaxCalculatorController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CongestionTaxCalculatorController.class);
	
	@Autowired
	ICalculatorService service;
	
	@Autowired
	IVehicleService vehicleService;
	
	@GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int calculate(@RequestBody InputModel inputModel) {
		
		validateInputModel(inputModel);
		
		return service.getTax(vehicleService.getVehicle(inputModel.getVehicleType()),
		                      sortDates(inputModel.getLocalDates()));
	}
	
	private void validateInputModel(InputModel inputModel) {
		
		LocalDateTime[] dates = inputModel.getLocalDates();
		
		if (dates == null || dates.length == 0) {
			String errorMessage = "No information about the dates.";
			InputModelValidationException ex = new InputModelValidationException(errorMessage);
			LOG.error(errorMessage, ex);
			
			throw ex;
		}
		
		String vehicleType = inputModel.getVehicleType();
		
		if (vehicleType == null || vehicleType.isEmpty()) {
			String errorMessage = "No information about the vehicle (null).";
			RuntimeException ex = new RuntimeException(errorMessage);
			LOG.error(errorMessage, ex);
		
			throw ex;
		}
	}
	
}
