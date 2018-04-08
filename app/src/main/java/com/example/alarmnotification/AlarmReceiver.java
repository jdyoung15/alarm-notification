package com.example.alarmnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.alarmnotification.activities.DisplayNotificationActivity;

/**
 * Created by jeremy on 3/26/18.
 */

public class AlarmReceiver extends BroadcastReceiver {
  public void onReceive(Context context, Intent intent) {
        /* Show a success toast*/
    //Toast.makeText(context, "Howdy partner", Toast.LENGTH_SHORT).show();
        /* Launch the MainActivity, just for fun */
    context.startActivity(new Intent(context, DisplayNotificationActivity.class));
  }
}
