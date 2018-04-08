package com.example.alarmnotification.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alarmnotification.AlarmReceiver;
import com.example.alarmnotification.R;
import com.example.alarmnotification.reminders.Reminder;
import com.example.alarmnotification.reminders.ReminderFiles;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class DisplayNotificationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_notification);

    LinearLayout layout = findViewById(R.id.reminder_list);

    List<Reminder> reminders = new ReminderFiles(this).getAllReminders();
    Collections.sort(reminders);

    for (Reminder reminder : reminders) {
      TextView textView = new TextView(this);
      textView.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(reminder.getDateTime()) + " " + reminder.getNote());
      textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      layout.addView(textView);
    }
    //cancelAlarm();
  }

  private void cancelAlarm() {
    // Request the AlarmManager object
    AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

    // Create the PendingIntent that would have launched the BroadcastReceiver
    PendingIntent pending = PendingIntent.getBroadcast(this, 1, new Intent(this, AlarmReceiver.class), 0);

    // Cancel the alarm associated with that PendingIntent
    manager.cancel(pending);
  }

}
