package com.example.alarmnotification.reminders;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Created by jeremy on 3/28/18.
 */

public class Reminder implements Serializable, Comparable<Reminder> {

  private int id;
  private String note;
  private ZonedDateTime dateTime;

  public Reminder(int id, String note, ZonedDateTime dateTime) {
    this.id = id;
    this.note = note;
    this.dateTime = dateTime;
  }

  public int getId() {
    return id;
  }

  public String getNote() {
    return note;
  }

  public ZonedDateTime getDateTime() {
    return dateTime;
  }

  @Override
  public int compareTo(Reminder b) {
    return this.dateTime.compareTo(b.getDateTime());
  }

  @Override
  public String toString() {
    return "REMINDER " + id + " " + dateTime + " " + note;
  }

}
