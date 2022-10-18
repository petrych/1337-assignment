package tech.petrych.congestion.calculator;

import org.junit.jupiter.api.Test;
import tech.petrych.congestion.calculator.service.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.petrych.congestion.calculator.service.DateUtil.parseInputAsLocalDateTime;

public class DateUtilTest {
	
	@Test
	void sortDates() {
		
		final List<String> dateStringsSorted = List.of(
				"2013-02-10 15:00:00",
				"2013-02-10 15:30:00",
				"2013-02-10 15:35:00",
				"2013-02-11 15:47:00",
				"2013-02-12 16:01:00"
		);
		final List<String> dateStringsUnsorted = List.of(
				"2013-02-10 15:35:00",
				"2013-02-11 15:47:00",
				"2013-02-10 15:00:00",
				"2013-02-12 16:01:00",
				"2013-02-10 15:30:00"
		);
		
		LocalDateTime[] expected = parseInputAsLocalDateTime(dateStringsSorted);
		
		LocalDateTime[] unsorted = parseInputAsLocalDateTime(dateStringsUnsorted);
		
		assertEquals(List.of(expected), List.of(DateUtil.sortDates(unsorted)));
		
	}
	
}
