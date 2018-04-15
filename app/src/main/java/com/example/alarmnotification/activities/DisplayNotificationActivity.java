package com.example.alarmnotification.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alarmnotification.R;
import com.example.alarmnotification.alarms.ReminderAlarm;
import com.example.alarmnotification.io.ReminderFile;
import com.example.alarmnotification.io.ReminderFiles;
import com.example.alarmnotification.reminders.Reminder;

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
      textView.setOnClickListener(v -> {
        layout.removeView(v);
        new ReminderFile(this, reminder.getId()).delete();
        new ReminderAlarm(this, reminder.getId()).cancel();
      });
      layout.addView(textView);
    }
    //cancelAlarm();
  }

}
