package com.example.alarmnotification.reminders;

import android.content.Context;

import com.example.alarmnotification.fileIO.ObjectFile;

public class ReminderFile extends ObjectFile {

  private static String REMINDER_FILENAME_ROOT = "reminder";
  private static String REMINDER_FILENAME_JOIN = "-";

  public ReminderFile(Context context, String filename) {
    super(context, filename);
  }


  public ReminderFile(Context context, int reminderId) {
    super(context, filename(reminderId));
  }

  public static String filename(int reminderId) {
    return String.join(REMINDER_FILENAME_JOIN, REMINDER_FILENAME_ROOT, String.valueOf(reminderId));
  }

  public static int extractId(String filename) {
    return Integer.parseInt(filename.split(REMINDER_FILENAME_JOIN)[1]);
  }

  public int id() {
    return extractId(super.getFilename());
  }

  public Reminder read() {
    Object o = super.read();
    return (Reminder) o;
  }

}
