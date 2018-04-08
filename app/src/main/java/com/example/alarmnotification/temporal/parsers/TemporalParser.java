package com.example.alarmnotification.temporal.parsers;

import java.time.temporal.Temporal;

public interface TemporalParser {

  Temporal parse(String text);

}
