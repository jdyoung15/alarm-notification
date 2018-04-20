package com.example.alarmnotification.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.alarmnotification.R;
import com.example.alarmnotification.reminders.Reminder;

public class ReminderNotification {

  Context context;
  Reminder reminder;

  public ReminderNotification(Context context, Reminder reminder) {
    this.context = context;
    this.reminder = reminder;
  }

  public void send() {
    Notification notification = createNotification();
    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
    // TODO should be unique id
    notificationManager.notify(2, notification);
  }

  private Notification createNotification() {
    ReminderNotificationManager manager = new ReminderNotificationManager(context);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, manager.getChannelId())
      .setSmallIcon(R.drawable.ic_stat_onesignal_default)
      .setContentTitle("Reminder")
      .setContentText(reminder.getNote())
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
      .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(), 0)) // to cancel notification on tap
      .setAutoCancel(true);

    return builder.build();
  }

}
