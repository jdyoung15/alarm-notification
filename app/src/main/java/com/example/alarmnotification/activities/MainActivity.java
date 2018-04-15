package com.example.alarmnotification.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.alarmnotification.R;
import com.example.alarmnotification.alarms.AlarmScheduler;
import com.example.alarmnotification.notifications.ReminderNotificationManager;
import com.example.alarmnotification.reminders.Reminder;
import com.example.alarmnotification.reminders.ReminderFile;
import com.example.alarmnotification.reminders.ReminderFiles;
import com.example.alarmnotification.userInput.UserInputParser;

import java.time.ZonedDateTime;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // TODO move this to application startup?
    ReminderNotificationManager manager = new ReminderNotificationManager(this);
    manager.createChannel();
  }

  public void processReminderMessage(View view) {
    Intent intent = new Intent(this, DisplayNotificationActivity.class);

    EditText editText = findViewById(R.id.editText);
    String userInput = editText.getText().toString();

    // parse info from user input
    UserInputParser parser = new UserInputParser();
    ZonedDateTime reminderDateTime = parser.parseDateTime(userInput);
    String reminderNote = parser.parseNote(userInput);

    // create Reminder and write to local storage
    int id = new ReminderFiles(this).getNextReminderId();
    Reminder reminder = new Reminder(id, reminderNote, reminderDateTime);
    new ReminderFile(this, id).write(reminder);

    // schedule alarm
    AlarmScheduler alarmScheduler = new AlarmScheduler(this);
    // as a precaution, cancel alarm if it already exists
    alarmScheduler.cancel(reminder.getId());
    alarmScheduler.schedule(reminder.getId(), reminder.getDateTime().toInstant().toEpochMilli());

    // TODO: return to MainActivity if user input is invalid

    startActivity(intent);
  }
}
