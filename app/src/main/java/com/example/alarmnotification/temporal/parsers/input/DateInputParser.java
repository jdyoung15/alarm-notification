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
    new DatePatternParser("MMM d yyyy"),
    new DatePatternParser("MMM d yy"),
    new PartialDatePatternParser("MMM d yyyy", " %d"),
    new DatePatternParser("MMMM d yyyy"),
    new DatePatternParser("MMMM d yy"),
    new PartialDatePatternParser("MMMM d yyyy", " %d"),
  };

  public DateInputParser() {
    super(DATE_PATTERNS);
  }

  public LocalDate parse(String text) {
    return (LocalDate) super.parse(text);
  }

  String format(String word) {
    if (word.isEmpty()) {
      return word;
    }
    return capitalizeFirstLetter(word);
  }

  private String capitalizeFirstLetter(String word) {
    return word.substring(0, 1).toUpperCase() + word.substring(1);
  }

}
