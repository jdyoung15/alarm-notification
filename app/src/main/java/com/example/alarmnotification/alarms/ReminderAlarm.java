package com.example.alarmnotification.alarms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class ReminderAlarm extends Alarm {

  public ReminderAlarm(Context context, int requestCode) {
    super(context, requestCode);
  }

  PendingIntent createPendingIntent(int requestCode) {
    Intent receiverIntent = new Intent(context, ReminderAlarmReceiver.class);
    // TODO magic string
    receiverIntent.putExtra("reminderId", requestCode);
    return PendingIntent.getBroadcast(context, requestCode, receiverIntent, 0);
  }

}
