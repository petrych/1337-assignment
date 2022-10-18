package tech.petrych.congestion.calculator.service;

import org.springframework.stereotype.Service;
import tech.petrych.congestion.calculator.model.TimeRule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@Service
public class DateRuleService implements IDateRuleService {
	
	private static final List<String> tollFreeDateStrings = List.of(
			"2013-01-01",
			"2013-01-05",
			"2013-01-06",
			"2013-03-28",
			"2013-03-29",
			"2013-03-30",
			"2013-03-31",
			"2013-04-01",
			"2013-04-30",
			"2013-05-01",
			"2013-05-08",
			"2013-05-09",
			"2013-05-17",
			"2013-05-18",
			"2013-05-19",
			"2013-06-05",
			"2013-06-06",
			"2013-06-21",
			"2013-06-22",
			"2013-07-01",
			"2013-07-02",
			"2013-07-03",
			"2013-07-04",
			"2013-07-05",
			"2013-07-06",
			"2013-07-07",
			"2013-07-08",
			"2013-07-09",
			"2013-07-10",
			"2013-07-11",
			"2013-07-12",
			"2013-07-13",
			"2013-07-14",
			"2013-07-15",
			"2013-07-16",
			"2013-07-17",
			"2013-07-18",
			"2013-07-19",
			"2013-07-20",
			"2013-07-21",
			"2013-07-22",
			"2013-07-23",
			"2013-07-24",
			"2013-07-25",
			"2013-07-26",
			"2013-07-27",
			"2013-07-28",
			"2013-07-29",
			"2013-07-30",
			"2013-07-31",
			"2013-11-01",
			"2013-11-02",
			"2013-12-24",
			"2013-12-25",
			"2013-12-26",
			"2013-12-31"
	);
	
	private LocalDate[] tollFreeDates;
	
	public DateRuleService() {
		tollFreeDates = getTollFreeDates();
	}
	
	
	@Override
	public List<TimeRule> getDateRules() {
		return List.of(
				new TimeRule(LocalTime.of(6, 0), LocalTime.of(6, 29), 8),
				new TimeRule(LocalTime.of(6, 30), LocalTime.of(6, 59), 13),
				new TimeRule(LocalTime.of(7, 0), LocalTime.of(7, 59), 18),
				new TimeRule(LocalTime.of(8, 0), LocalTime.of(8, 29), 13),
				new TimeRule(LocalTime.of(8, 30), LocalTime.of(14, 59), 8),
				new TimeRule(LocalTime.of(15, 0), LocalTime.of(15, 29), 13),
				new TimeRule(LocalTime.of(15, 30), LocalTime.of(16, 59), 18),
				new TimeRule(LocalTime.of(17, 0), LocalTime.of(17, 59), 13),
				new TimeRule(LocalTime.of(18, 0), LocalTime.of(18, 29), 8),
				new TimeRule(LocalTime.of(18, 30), LocalTime.of(05, 59), 8)
		);
	}
	
	@Override
	public LocalDate[] getTollFreeDates() {
		
		LocalDate[] tollFreeDates = new LocalDate[tollFreeDateStrings.size()];
		
		tollFreeDateStrings.forEach(d -> {
			LocalDate date = LocalDate.parse(d, ISO_LOCAL_DATE);
			tollFreeDates[tollFreeDateStrings.indexOf(d)] = date;
		});
		
		return tollFreeDates;
	}
	
	@Override
	public boolean isTollFreeDate(LocalDateTime date) {
		if (date == null) return false;
		
		return isWeekEnd(date) || Arrays.stream(tollFreeDates).findFirst().isPresent();
	}
	
	private boolean isWeekEnd(LocalDateTime date) {
		
		DayOfWeek day = date.getDayOfWeek();
		return (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY));
	}
}
