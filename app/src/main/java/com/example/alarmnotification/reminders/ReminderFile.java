package com.example.alarmnotification.reminders;

import android.content.Context;

import com.example.alarmnotification.fileIO.ObjectFile;

public class ReminderFile extends ObjectFile {

  public static String REMINDER_FILENAME_ROOT = "reminder";
  public static String REMINDER_FILENAME_JOIN = "-";

  public ReminderFile(Context context, String string) {
    super(context, string);
  }


  public ReminderFile(Context context, int id) {
    super(context, filename(id));
  }

  public static String filename(int id) {
    return String.join(REMINDER_FILENAME_JOIN, REMINDER_FILENAME_ROOT, String.valueOf(id));
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
