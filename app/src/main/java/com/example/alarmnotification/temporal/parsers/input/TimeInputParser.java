package com.example.alarmnotification.temporal.parsers.input;

import com.example.alarmnotification.temporal.parsers.pattern.TimePatternParser;

import java.time.LocalTime;

public class TimeInputParser extends TemporalInputParser {

  private static final TimePatternParser[] TIME_PATTERNS = {
    new TimePatternParser("ha"),       // 8PM
    new TimePatternParser("h:mma"),    // 8:30PM
    new TimePatternParser("hmma"),     // 830PM
    new TimePatternParser("h a"),       // 8 PM
    new TimePatternParser("h:mm a"),    // 8:30 PM
    new TimePatternParser("hmm a"),     // 830 PM
    new TimePatternParser("k:mm"),     // 8:30
    //new TimePatternParser("kmm"),      // 830
    //new TimePatternParser("k")         // 8
  };

  public TimeInputParser() {
    super(TIME_PATTERNS);
  }

  public LocalTime parse(String text) {
    return (LocalTime) super.parse(text);
  }

  String format(String word) {
    return word
      .toUpperCase() // am --> AM
      .replace(".", "") // P.M. -> PM
      .replaceAll("A$", "AM") // 8A -> 8AM
      .replaceAll("P$", "PM");
  }

}
