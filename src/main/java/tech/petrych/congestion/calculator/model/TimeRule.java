package tech.petrych.congestion.calculator.model;

import java.time.LocalTime;

public class DateRule {
	
	
	LocalTime startPeriod;
	
	LocalTime endPeriod;
	
	int amount; // maybe worth BigDecimal
	
	public DateRule(LocalTime startPeriod, LocalTime endPeriod, int amount) {
		
		this.startPeriod = startPeriod;
		this.endPeriod = endPeriod;
		this.amount = amount;
	}
	
	
	public LocalTime getStartPeriod() {
		
		return startPeriod;
	}
	
	public void setStartPeriod(LocalTime startPeriod) {
		
		this.startPeriod = startPeriod;
	}
	
	public LocalTime getEndPeriod() {
		
		return endPeriod;
	}
	
	public void setEndPeriod(LocalTime endPeriod) {
		
		this.endPeriod = endPeriod;
	}
	
	public int getAmount() {
		
		return amount;
	}
	
	public void setAmount(int amount) {
		
		this.amount = amount;
	}
	
	
}
