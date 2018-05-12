package com.example.alarmnotification.temporal.formatters;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeDisplayFormatter {

  public static String format(LocalTime time) {
    String timeDisplay;
    if (time.getMinute() == 0) {
      timeDisplay = DateTimeFormatter.ofPattern("h").format(time);
    }
    else {
      timeDisplay = DateTimeFormatter.ofPattern("h:mm").format(time);
    }

    return timeDisplay + amPmDisplayText(time);
  }

  private static String amPmDisplayText(LocalTime time) {
    return (time.isBefore(LocalTime.NOON)) ? "a" : "p";
  }

}
