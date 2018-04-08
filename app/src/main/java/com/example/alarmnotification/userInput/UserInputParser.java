package com.example.alarmnotification.userInput;

import com.example.alarmnotification.temporal.parsers.input.DateInputParser;
import com.example.alarmnotification.temporal.parsers.input.TimeInputParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UserInputParser {

  private TimeInputParser timeParser;
  private DateInputParser dateParser;

  public UserInputParser() {
    this.timeParser = new TimeInputParser();
    this.dateParser = new DateInputParser();
  }

  public ZonedDateTime parseDateTime(String userInput) {
    LocalTime time = timeParser.parse(userInput);
    LocalDate date = dateParser.parse(userInput);

    if (time == null) {
      time = defaultTime();
    }

    if (date == null) {
      date = defaultDate(time);
    }

    return ZonedDateTime.of(date, time, ZoneId.systemDefault());
  }

  public String parseNote(String userInput) {
    List<String> words = new LinkedList<>(Arrays.asList(userInput.trim().split("\\s+")));

    List<Integer> parsedPositions = timeParser.parsedPositions(userInput);
    parsedPositions.addAll(dateParser.parsedPositions(userInput));
    Collections.sort(parsedPositions);
    Collections.reverse(parsedPositions);

    // remove parsed words, in reverse order
    for (int position : parsedPositions) {
      words.remove(position);
    }

    return String.join(" ", words);
  }

  private LocalTime defaultTime() {
    return LocalTime.of(20, 0); // 8pm
  }

  public LocalDate defaultDate(LocalTime time) {
    LocalDate today = LocalDate.now();
    if (time.isBefore(LocalTime.now())) {
      return today.plusDays(1);
    }
    return today;
  }

}
