package com.example.alarmnotification.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.alarmnotification.notifications.ReminderNotification;
import com.example.alarmnotification.reminders.Reminder;
import com.example.alarmnotification.reminders.ReminderFile;

/**
 * Created by jeremy on 3/26/18.
 */

public class AlarmReceiver extends BroadcastReceiver {

  public void onReceive(Context context, Intent intent) {
    // TODO handle if default value
    int reminderId = intent.getIntExtra("reminderId", -1);
    ReminderFile file = new ReminderFile(context, reminderId);
    Reminder reminder = file.read();
    // TODO delete file
    file.delete();
    ReminderNotification notification = new ReminderNotification(context, reminder);
    notification.send();
  }

}
