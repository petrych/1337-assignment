package tech.petrych.congestion.calculator.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtil {
	
	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	
	public static ZoneOffset zoneOffset = ZoneOffset.UTC;
	
	
	public static LocalDateTime[] sortDates(LocalDateTime[] datesToSort) {
		
		List<LocalDateTime> list = Arrays.stream(datesToSort).collect(Collectors.toCollection(ArrayList::new));
		Collections.sort(list);
		
		LocalDateTime[] sortedArray = new LocalDateTime[list.size()];
		list.toArray(sortedArray);
		
		return sortedArray;
	}
	
	public static LocalDateTime[] parseInputAsLocalDateTime(List<String> dateStrings) {
		
		LocalDateTime[] dates = new LocalDateTime[dateStrings.size()];
		
		dateStrings.forEach(d -> {
			LocalDateTime date = LocalDateTime.parse(d, dateTimeFormatter);
			dates[dateStrings.indexOf(d)] = date;
		});
		
		return sortDates(dates);
	}
	
	public static long toMillis(LocalDateTime localDateTime) {
		
		return localDateTime.atZone(zoneOffset).toInstant().toEpochMilli();
	}
	
	public static LocalDateTime convertFromString(String dateTimeString) {
		
		return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
	}
	
}
