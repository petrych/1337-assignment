package tech.petrych.congestion.calculator.service;

import tech.petrych.congestion.calculator.model.IVehicle;

import java.time.LocalDateTime;

public interface ICalculatorService {
	
	int getTax(IVehicle vehicle, LocalDateTime[] dates);
	
	int getTollFee(LocalDateTime date, IVehicle vehicle);
	
}
