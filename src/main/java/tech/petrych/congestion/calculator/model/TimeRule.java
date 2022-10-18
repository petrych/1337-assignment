package tech.petrych.congestion.calculator.model;

import java.time.LocalTime;

public class TimeRule {
	
	LocalTime startTime;
	
	LocalTime endTime;
	
	int amount; // maybe worth BigDecimal
	
	public TimeRule(LocalTime startTime, LocalTime endTime, int amount) {
		
		this.startTime = startTime;
		this.endTime = endTime;
		this.amount = amount;
	}
	
	
	public LocalTime getStartTime() {
		
		return startTime;
	}
	
	public void setStartTime(LocalTime startTime) {
		
		this.startTime = startTime;
	}
	
	public LocalTime getEndTime() {
		
		return endTime;
	}
	
	public void setEndTime(LocalTime endTime) {
		
		this.endTime = endTime;
	}
	
	public int getAmount() {
		
		return amount;
	}
	
	public void setAmount(int amount) {
		
		this.amount = amount;
	}
	
}
