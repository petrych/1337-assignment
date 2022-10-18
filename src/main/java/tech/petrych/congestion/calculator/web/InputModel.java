package tech.petrych.congestion.calculator.web;

import tech.petrych.congestion.calculator.service.DateUtil;

import java.time.LocalDateTime;

public class InputModel {
	
	private String vehicle;
	
	private String[] dates;
	
	public InputModel(String vehicle, String[] dates) {
		
		this.vehicle = vehicle;
		this.dates = dates;
	}
	
	public String getVehicleType() {
		
		return vehicle;
	}
	
	public void setVehicle(String vehicle) {
		
		this.vehicle = vehicle;
	}
	
	public String[] getDates() {
		
		return dates;
	}
	
	public LocalDateTime[] getLocalDates() {
		
		LocalDateTime[] convertedDates = new LocalDateTime[this.dates.length];
		for (int i = 0; i < this.dates.length; i++) {
			convertedDates[i] = DateUtil.convertFromString(this.dates[i]);
		}
		return convertedDates;
	}
	
	public void setDates(String[] dates) {
		
		this.dates = dates;
	}
	
}
