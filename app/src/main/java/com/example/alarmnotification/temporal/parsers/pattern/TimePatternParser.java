package com.example.alarmnotification.temporal.parsers.pattern;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePatternParser extends TemporalPatternParser {

  public TimePatternParser(String pattern) {
    super(pattern);
  }

  LocalTime parse(String text, DateTimeFormatter formatter) {
    return LocalTime.parse(text, formatter);
  }

}
