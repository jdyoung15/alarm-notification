package com.example.alarmnotification.temporal.parsers.pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PartialDatePatternParser extends DatePatternParser {

  String yearFormat;

  public PartialDatePatternParser(String pattern, String yearFormat) {
    super(pattern);
    this.yearFormat = yearFormat;
  }

  public LocalDate parse(String text, DateTimeFormatter formatter) {
    int year = LocalDate.now().getYear(); // TODO this could be next year, not current year
    String yearString = String.format(yearFormat, year);
    String phraseWithYear = text.concat(yearString);
    return super.parse(phraseWithYear, formatter);
  }

  public int numWordsToParse() {
    int numWords = super.numWordsToParse();
    if (numWords > 1) {
      numWords -= 1; // e.g. "M d YYYY" --> 2 words to parse, since we provide the YYYY part
    }
    return numWords;
  }

}
