package tech.petrych.congestion.calculator.service;

import org.springframework.stereotype.Service;
import tech.petrych.congestion.calculator.model.Car;
import tech.petrych.congestion.calculator.model.IVehicle;
import tech.petrych.congestion.calculator.model.Military;
import tech.petrych.congestion.calculator.model.Motorcycle;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {
	
	private List<String> tollFreeVehicles;
	
	
	public VehicleService() {
		
		this.tollFreeVehicles = getTollFreeVehicles();
	}
	
	@Override
	public IVehicle getVehicle(String vehicleType) {
		
		switch (vehicleType) {
			case "car":
				return new Car();
			case "military":
				return new Military();
			case "motorcycle":
				return new Motorcycle();
		}
		return null;
	}
	
	@Override
	public List<String> getTollFreeVehicles() {
		
		return List.of(
				"emergency",
				"bus",
				"diplomat",
				"motorcycle",
				"military",
				"foreign"
		);
	}
	
	@Override
	public boolean isTollFreeVehicle(IVehicle vehicle) {
		
		if (vehicle == null) return false;
		String vehicleType = vehicle.getVehicleType().toLowerCase();
		
		return tollFreeVehicles.contains(vehicleType);
	}
	
}
