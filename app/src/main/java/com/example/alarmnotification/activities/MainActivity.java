package com.example.alarmnotification.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.alarmnotification.R;
import com.example.alarmnotification.RemindersAdapter;
import com.example.alarmnotification.alarms.ReminderAlarm;
import com.example.alarmnotification.io.ReminderFile;
import com.example.alarmnotification.io.ReminderFiles;
import com.example.alarmnotification.notifications.ReminderNotificationManager;
import com.example.alarmnotification.reminders.Reminder;
import com.example.alarmnotification.userInput.UserInputParser;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView remindersView;
  private RecyclerView.Adapter remindersAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // TODO move this to application startup?
    ReminderNotificationManager manager = new ReminderNotificationManager(this);
    manager.createChannel();

    remindersView = findViewById(R.id.reminder_list);

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    //remindersView.setHasFixedSize(true);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    remindersView.setLayoutManager(layoutManager);

    List<Reminder> reminders = new ReminderFiles(this).getAllReminders();
    Collections.sort(reminders);

    // specify an adapter (see also next example)
    remindersAdapter = new RemindersAdapter(this, reminders);
    remindersView.setAdapter(remindersAdapter);

    RecyclerView.ItemDecoration divider =
      new DividerItemDecoration(remindersView.getContext(), layoutManager.getOrientation());
    remindersView.addItemDecoration(divider);
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
