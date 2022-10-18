package tech.petrych.congestion.calculator.service;

import tech.petrych.congestion.calculator.model.IVehicle;

import java.util.List;

public interface IVehicleService {
	
	IVehicle getVehicle(String vehicleType);
	
	List<String> getTollFreeVehicles();
	
	boolean isTollFreeVehicle(IVehicle vehicle);
	
}
