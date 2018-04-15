package com.example.alarmnotification.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.alarmnotification.reminders.Reminder;

public class AlarmScheduler {

  private Context context;
  private Reminder reminder;

  public AlarmScheduler(Context context, Reminder reminder) {
    this.context = context;
    this.reminder = reminder;
  }

  public void schedule() {
    Intent receiverIntent = new Intent(context, AlarmReceiver.class);
    // TODO magic string
    receiverIntent.putExtra("reminderId", reminder.getId());

    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, reminder.getId(), receiverIntent, 0);

    AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    manager.set(AlarmManager.RTC_WAKEUP, reminder.getDateTime().toInstant().toEpochMilli(), pendingIntent);
  }

}
