package com.example.alarmnotification.temporal.parsers.pattern;

import com.example.alarmnotification.temporal.parsers.TemporalParser;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

public abstract class TemporalPatternParser implements TemporalParser {

  String pattern;
  DateTimeFormatter formatter;

  public TemporalPatternParser(String pattern) {
    this.pattern = pattern;
    formatter = DateTimeFormatter.ofPattern(pattern);
  }

  public Temporal parse(String text) {
    Temporal temporal = null;
    try {
      temporal = parse(text, formatter);
    }
    catch (DateTimeParseException e) { }
    return temporal;
  }

  abstract Temporal parse(String phrase, DateTimeFormatter formatter);

  public int numWordsToParse() {
    String trimmed = pattern.trim();
    return trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
  }

}
