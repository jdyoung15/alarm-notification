package com.example.alarmnotification.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.alarmnotification.AlarmReceiver;
import com.example.alarmnotification.R;
import com.example.alarmnotification.reminders.Reminder;
import com.example.alarmnotification.reminders.ReminderFile;
import com.example.alarmnotification.reminders.ReminderFiles;
import com.example.alarmnotification.userInput.UserInputParser;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //writeToFile();

    //scheduleAlarm();
  }

  private void writeToFile() {
    ZonedDateTime dateTime = ZonedDateTime.of(2018, 3, 30, 14, 0, 0, 0, ZoneId.systemDefault());
    int id = new ReminderFiles(this).getNextReminderId();
    Reminder reminder = new Reminder(id, "asdf", dateTime);
    new ReminderFile(this, id).write(reminder);
  }

  private void scheduleAlarm() {
    AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    PendingIntent pendingZero = PendingIntent.getBroadcast(this, 0, new Intent(this, AlarmReceiver.class), 0);
    //PendingIntent pendingOne = PendingIntent.getBroadcast(this, 1, new Intent(this, AlarmReceiver.class), 0);
    ZonedDateTime zonedDateTime =
      ZonedDateTime.of(2018, 3, 26, 20, 58, 20, 0, ZoneId.systemDefault());
    manager.set(AlarmManager.RTC_WAKEUP, zonedDateTime.toInstant().toEpochMilli(), pendingZero);
    //manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 15 * 1000, pendingOne);
    //manager.cancel(pendingZero);
  }

  public void processReminderMessage(View view) {
    //writeToFile();
    Intent intent = new Intent(this, DisplayNotificationActivity.class);

    // get info from edit field
    EditText editText = findViewById(R.id.editText);
    String userInput = editText.getText().toString();
    //intent.putExtra(EXTRA_MESSAGE, message);
    UserInputParser parser = new UserInputParser();
    ZonedDateTime reminderDateTime = parser.parseDateTime(userInput);
//    reminderDateTime =
//      ZonedDateTime.of(2018, 3, 31, 14, 0, 0, 0, ZoneId.systemDefault());
    String reminderNote = parser.parseNote(userInput);

    // create reminder object
    int id = new ReminderFiles(this).getNextReminderId();
    Reminder reminder = new Reminder(id, reminderNote, reminderDateTime);

    // schedule alarm
    //scheduleAlarm(reminderDateTime);

    // write reminder
    new ReminderFile(this, id).write(reminder);

    // TODO: return to MainActivity if user input is invalid

    startActivity(intent);
  }
}
