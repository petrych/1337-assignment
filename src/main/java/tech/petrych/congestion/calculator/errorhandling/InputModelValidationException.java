package tech.petrych.congestion.calculator.errorhandling;

public class InputModelValidationException extends RuntimeException {
	
	public static final String NO_INFORMATION_ABOUT_THE_DATES = "No information about the dates.";
	
	public static final String NO_INFORMATION_ABOUT_THE_VEHICLE = "No information about the vehicle.";
	
	
	public InputModelValidationException(String message) {
		
		super(message);
	}
	
}
