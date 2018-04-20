package com.example.alarmnotification.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alarmnotification.R;
import com.example.alarmnotification.alarms.ReminderAlarm;
import com.example.alarmnotification.io.ReminderFile;
import com.example.alarmnotification.io.ReminderFiles;
import com.example.alarmnotification.notifications.ReminderNotificationManager;
import com.example.alarmnotification.reminders.Reminder;
import com.example.alarmnotification.userInput.UserInputParser;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // TODO move this to application startup?
    ReminderNotificationManager manager = new ReminderNotificationManager(this);
    manager.createChannel();

    LinearLayout layout = findViewById(R.id.reminder_list);

    List<Reminder> reminders = new ReminderFiles(this).getAllReminders();
    Collections.sort(reminders);

    for (Reminder reminder : reminders) {
      if (reminder.getDateTime().isBefore(ZonedDateTime.now())) {
        new ReminderFile(this, reminder.getId()).delete();
        continue;
      }

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

  }

  public void processReminderMessage(View view) {
    //Intent intent = new Intent(this, MainActivity.class);

    EditText editText = findViewById(R.id.editText);
    String userInput = editText.getText().toString();
    editText.setText("");

    // parse info from user input
    UserInputParser parser = new UserInputParser();
    ZonedDateTime reminderDateTime = parser.parseDateTime(userInput);
    String reminderNote = parser.parseNote(userInput);

    // create Reminder and write to local storage
    int id = new ReminderFiles(this).getNextReminderId();
    Reminder reminder = new Reminder(id, reminderNote, reminderDateTime);
    new ReminderFile(this, id).write(reminder);

    // schedule alarm
    ReminderAlarm alarmScheduler = new ReminderAlarm(this, reminder.getId());
    // as a precaution, cancel alarm if it already exists
    alarmScheduler.cancel();
    alarmScheduler.schedule(reminder.getDateTime().toInstant().toEpochMilli());

    // TODO: handle if user input is invalid

//    finish();
//    startActivity(getIntent());
    recreate();
  }
}
