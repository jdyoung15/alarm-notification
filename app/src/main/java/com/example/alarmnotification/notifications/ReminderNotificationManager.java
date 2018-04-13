package com.example.alarmnotification.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class ReminderNotificationManager {

  public static final String CHANNEL_ID = "reminder_notification_channel";
  public static final String CHANNEL_NAME = "Reminder Notification Channel";
  public static final String CHANNEL_DESCRIPTION = "Notification channel for standard reminder notifications";
  private Context context;

  public ReminderNotificationManager(Context context) {
    this.context = context;
  }

  @RequiresApi(Build.VERSION_CODES.O)
  public String getChannelId() {
    return CHANNEL_ID;
  }

  @RequiresApi(Build.VERSION_CODES.O)
  public void createChannel() {
    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
    channel.setDescription(CHANNEL_DESCRIPTION);

    // Register the channel with the system
    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.createNotificationChannel(channel);
  }

}
