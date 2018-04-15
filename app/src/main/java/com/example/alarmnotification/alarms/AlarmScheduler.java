package com.example.alarmnotification.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmScheduler {

  private Context context;
  private AlarmManager manager;

  public AlarmScheduler(Context context) {
    this.context = context;
    this.manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
  }

  public void schedule(int requestCode, long millis) {
    manager.set(AlarmManager.RTC_WAKEUP, millis, getPendingIntent(requestCode));
  }

  public void cancel(int requestCode) {
    manager.cancel(getPendingIntent(requestCode));
  }

  private PendingIntent getPendingIntent(int requestCode) {
    Intent receiverIntent = new Intent(context, AlarmReceiver.class);
    // TODO magic string
    receiverIntent.putExtra("requestCode", requestCode);
    return PendingIntent.getBroadcast(context, requestCode, receiverIntent, 0);
  }

}
