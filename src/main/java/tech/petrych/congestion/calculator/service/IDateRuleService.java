package tech.petrych.congestion.calculator.service;

import tech.petrych.congestion.calculator.model.TimeRule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IDateRuleService {
	
	List<TimeRule> getTimeRules();
	
	int getAmountFee(LocalDateTime localTime);
	
	LocalDate[] getTollFreeDates();
	
	boolean isTollFreeDate(LocalDateTime date);
	
}
