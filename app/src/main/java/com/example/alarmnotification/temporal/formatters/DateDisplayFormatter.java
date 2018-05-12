package com.example.alarmnotification.temporal.formatters;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateDisplayFormatter {

  /**
   * Assumes @date is >= today and @time is >= now.
   * @param date
   * @param time
   * @return
   */
  public static String format(LocalDate date, LocalTime time) {
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plusDays(1);
    LocalTime now = LocalTime.now();

    // reminder set for later today (now - 11:59:59pm)
    if (date.isEqual(today)) {
      return "";
    }
    // reminder set for tomorrow
    else if (date.isEqual(tomorrow)) {
      // reminder set for 12am - 4am tomorrow
      if (time.isBefore(LocalTime.of(4, 0))) {
        // currently AM
        if (now.isBefore(LocalTime.NOON)) {
          return "Tonight";
        }
        // currently PM
        else {
          return "";
        }
      }
      // reminder set for 4am - 11:59:59 tomorrow
      else {
        return "Tomorrow";
      }
    }
    // reminder set for after tomorrow
    else {
      return DateTimeFormatter.ofPattern("M/d").format(date);
    }
  }

}
