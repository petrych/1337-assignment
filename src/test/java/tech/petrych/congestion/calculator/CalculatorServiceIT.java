package tech.petrych.congestion.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.petrych.congestion.calculator.model.Car;
import tech.petrych.congestion.calculator.model.Military;
import tech.petrych.congestion.calculator.service.CalculatorService;
import tech.petrych.congestion.calculator.service.DateTimeRuleService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.petrych.congestion.calculator.service.DateUtil.parseInputAsLocalDateTime;

@SpringBootTest
public class CalculatorServiceIT {
	
	@Autowired
	private CalculatorService calculatorService;
	
	@Autowired
	private DateTimeRuleService dateTimeRuleService;
	
	// Assuming that 60min 00 sec is greater than 60 min
	@Test
	void givenOrdinaryVehicle_whenMoreThan60Min_thenTwoFees() {
		
		final List<String> dateStrings = List.of(
				"2013-01-14 11:00:00",
				"2013-01-14 12:00:01",
				"2013-01-14 12:30:01"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsLocalDateTime(dateStrings));
		
		assertEquals(8 * 2, tax);
		
	}
	
	
	@Test
	void givenTaxExemptVehicle_whenTollDate_thanFeeZero() {
		
		final List<String> dateStrings = List.of(
				"2013-02-07 06:23:27",
				"2013-02-07 15:27:00"
		);
		
		int tax = calculatorService.getTax(new Military(), parseInputAsLocalDateTime(dateStrings));
		
		assertEquals(0, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenFreeDate_thanFeeZero() {
		
		final List<String> dateStrings = List.of(
				"2013-03-28 14:07:27"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsLocalDateTime(dateStrings));
		
		assertEquals(0, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenWeekend_thanFeeZero() {
		
		final List<String> dateStrings = List.of(
				"2013-02-10 12:07:25"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsLocalDateTime(dateStrings));
		
		assertEquals(0, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenJuly_thanFeeZero() {
		
		final List<String> dateStrings = List.of(
				"2013-07-10 12:07:50",
				"2013-07-10 12:07:30",
				"2013-07-10 12:07:40"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsLocalDateTime(dateStrings));
		
		assertEquals(0, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenTollDate_thanFeePresent() {
		
		final List<String> dateStrings = List.of(
				"2013-02-07 06:23:27",
				"2013-02-07 15:27:00"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsLocalDateTime(dateStrings));
		
		assertEquals(8 + 13, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenWithin60Min_thanFeeIsMax() {
		
		final List<String> dateStrings = List.of(
				"2013-02-08 15:47:00",
				"2013-02-08 16:01:00",
				"2013-02-08 15:29:00"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsLocalDateTime(dateStrings));
		
		assertEquals(18, tax);
	}
	
	
}
