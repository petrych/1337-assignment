package tech.petrych.congestion.calculator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.petrych.congestion.calculator.model.IVehicle;
import tech.petrych.congestion.calculator.service.ICalculatorService;
import tech.petrych.congestion.calculator.service.IVehicleService;

import java.time.LocalDateTime;

import static tech.petrych.congestion.calculator.service.DateUtil.sortDates;

@RestController
public class CongestionTaxCalculatorController {
	
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
		
		if (dates == null || dates.length == 0)
			throw new RuntimeException("No information about the dates.");
		
		IVehicle vehicle = vehicleService.getVehicle(inputModel.getVehicleType());
		if (vehicle == null)
			throw new RuntimeException("No information about the vehicle (null).");
	}
	
}
