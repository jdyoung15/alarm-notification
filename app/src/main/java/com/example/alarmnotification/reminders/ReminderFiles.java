package com.example.alarmnotification.reminders;

import android.content.Context;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReminderFiles {

  private static final int STARTING_ID = 1;

  private Context context;

  public ReminderFiles(Context context) {
    this.context = context;
  }

  public List<Reminder> getAllReminders() {
    List<String> filenames = Arrays.asList(context.fileList());

    return filenames
        .stream()
        .map(f -> new ReminderFile(context, f).read())
        .collect(Collectors.toList());
  }

  public int getNextReminderId() {
    List<String> filenames = Arrays.asList(context.fileList());

    if (filenames.isEmpty()) {
      return STARTING_ID;
    }

    List<Integer> ids = filenames
        .stream()
        .map(f -> ReminderFile.extractId(f))
        .collect(Collectors.toList());

    return Collections.max(ids) + 1;
  }

}
