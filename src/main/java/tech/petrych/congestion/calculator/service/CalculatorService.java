package tech.petrych.congestion.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.petrych.congestion.calculator.model.IVehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static tech.petrych.congestion.calculator.service.DateUtil.toMillis;

@Service
public class CalculatorService implements ICalculatorService {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private DateTimeRuleService dateTimeRuleService;
	
	
	@Override
	public int getTax(IVehicle vehicle, LocalDateTime[] dates) {
		
		LocalDateTime intervalStart = dates[0];
		int totalFee = 0;
		List<Integer> intervalFees = new ArrayList<>();
		
		for (int i = 0; i < dates.length; i++) {
			LocalDateTime date = dates[i];
			int nextFee = getTollFee(date, vehicle);
			if (intervalFees.isEmpty())
				intervalFees.add(getTollFee(intervalStart, vehicle));
			
			long diffInMillies = toMillis(date) - toMillis(intervalStart);
			long seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillies);
			
			if (seconds < 3600) {
				if (totalFee > 0) totalFee -= Collections.max(intervalFees);
				intervalFees.add(nextFee);
				totalFee += Collections.max(intervalFees);
			} else {
				intervalStart = dates[i];
				totalFee += nextFee;
				intervalFees.clear();
			}
			
			if (totalFee > 60) return 60;
		}
		
		return totalFee;
	}
	
	@Override
	public int getTollFee(LocalDateTime date, IVehicle vehicle) {
		
		if (dateTimeRuleService.isTollFreeDate(date) || vehicleService.isTollFreeVehicle(vehicle))
			return 0;
		
		return dateTimeRuleService.getAmountFee(date);
	}
	
}