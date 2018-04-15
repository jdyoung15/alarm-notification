package com.example.alarmnotification.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

public abstract class Alarm {

  Context context;
  AlarmManager manager;
  int requestCode;

  public Alarm(Context context, int requestCode) {
    this.context = context;
    this.manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    this.requestCode = requestCode;
  }

  public void schedule(long millis) {
    manager.set(AlarmManager.RTC_WAKEUP, millis, createPendingIntent(requestCode));
  }

  public void cancel() {
    manager.cancel(createPendingIntent(requestCode));
  }

  abstract PendingIntent createPendingIntent(int requestCode);

}
