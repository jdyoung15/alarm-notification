package com.example.alarmnotification.temporal.parsers.input;

import com.example.alarmnotification.temporal.parsers.pattern.DatePatternParser;
import com.example.alarmnotification.temporal.parsers.pattern.PartialDatePatternParser;

import java.time.LocalDate;

public class DateInputParser extends TemporalInputParser {

  private static final DatePatternParser[] DATE_PATTERNS = {
    new DatePatternParser("M/d/yyyy"),
    new DatePatternParser("M/d/yy"),
    new PartialDatePatternParser("M/d/yyyy", "/%d"),
    new DatePatternParser("M-d-yyyy"),
    new DatePatternParser("M-d-yy"),
    new PartialDatePatternParser("M-d-yyyy", "-%d"),
  };

  public DateInputParser() {
    super(DATE_PATTERNS);
  }

  public LocalDate parse(String text) {
    return (LocalDate) super.parse(text);
  }

  String format(String word) {
    return word;
  }

}
