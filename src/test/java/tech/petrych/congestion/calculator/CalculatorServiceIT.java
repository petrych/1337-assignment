import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.petrych.congestion.calculator.model.Car;
import tech.petrych.congestion.calculator.model.Military;
import tech.petrych.congestion.calculator.service.CongestionTaxCalculatorService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.petrych.congestion.calculator.model.DateConstants.DATE_FORMAT;

@SpringBootTest
public class CalculatorServiceIT {
	
	@Autowired
	private CongestionTaxCalculatorService calculatorService;
	
	private static DateTimeFormatter dateTimeFormatter;
	private static SimpleDateFormat simpleDateFormatter; // todo - to remove
	
	@BeforeAll
	public static void init() {
		
		dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
	}
	
	// Assuming that 60min 00 sec is greater than 60 min
	@Test
	void givenOrdinaryVehicle_whenMoreThan60Min_thanTwoFees() {
		
		final List<String> dateStrings = List.of(
				"2013-01-14 11:00:00",
				"2013-01-14 12:00:00"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsDeprecatedDate(dateStrings));
		
		assertEquals(8*2, tax);
		
	}
	
	
	@Test
	void givenTaxExemptVehicle_whenTollDate_thanFeeZero() {
		
		final List<String> dateStrings = List.of(
				"2013-02-07 06:23:27",
				"2013-02-07 15:27:00"
		);
		
		int tax = calculatorService.getTax(new Military(), parseInputAsDeprecatedDate(dateStrings));
		
		assertEquals(0, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenFreeDate_thanFeeZero() {
		
		final List<String> dateStrings = List.of(
				"2013-03-28 14:07:27"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsDeprecatedDate(dateStrings));
		
		assertEquals(0, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenWeekend_thanFeeZero() {
		
		final List<String> dateStrings = List.of(
				"2013-02-10 12:07:25"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsDeprecatedDate(dateStrings));
		
		assertEquals(0, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenJuly_thanFeeZero() {
		
		final List<String> dateStrings = List.of(
				"2013-07-10 12:07:25"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsDeprecatedDate(dateStrings));
		
		assertEquals(0, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenTollDate_thanFeePresent() {
		
		final List<String> dateStrings = List.of(
				"2013-02-07 06:23:27",
				"2013-02-07 15:27:00"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsDeprecatedDate(dateStrings));
		
		assertEquals(8 + 13, tax);
	}
	
	@Test
	void givenOrdinaryVehicle_whenWithin60Min_thanFeeIsMax() {
		final List<String> dateStrings = List.of(
				"2013-02-08 15:29:00",
				"2013-02-08 15:47:00",
				"2013-02-08 16:01:00"
		);
		
		int tax = calculatorService.getTax(new Car(), parseInputAsDeprecatedDate(dateStrings));
		
		assertEquals(18, tax);
	}
	
	
	private LocalDateTime[] parseInputAsLocalDateTime(List<String> dateStrings) {
		
		LocalDateTime[] dates = new LocalDateTime[dateStrings.size()];
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		
		dateStrings.forEach(d -> {
			LocalDateTime date = LocalDateTime.parse(d, dateTimeFormatter);
			dates[dateStrings.indexOf(d)] = date;
		});
		
		return dates;
	}
	
	private Date[] parseInputAsDeprecatedDate(List<String> dateStrings) {
		
		Date[] dates = new Date[dateStrings.size()];
		
		dateStrings.forEach(d -> {
			Date date = null;
			try {
				date = simpleDateFormatter.parse(d);
			} catch (ParseException e) {
				throw new RuntimeException(e); // todo
			}
			dates[dateStrings.indexOf(d)] = date;
		});
		
		return dates;
	}
}
