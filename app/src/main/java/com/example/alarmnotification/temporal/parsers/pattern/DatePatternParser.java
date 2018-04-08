package com.example.alarmnotification.temporal.parsers.pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePatternParser extends TemporalPatternParser {

  public DatePatternParser(String pattern) {
    super(pattern);
  }

  LocalDate parse(String text, DateTimeFormatter formatter) {
    return LocalDate.parse(text, formatter);
  }

}
