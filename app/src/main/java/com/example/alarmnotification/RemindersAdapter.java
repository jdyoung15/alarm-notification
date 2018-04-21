package com.example.alarmnotification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alarmnotification.alarms.ReminderAlarm;
import com.example.alarmnotification.io.ReminderFile;
import com.example.alarmnotification.reminders.Reminder;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ViewHolder> {

  private Context context;
  private List<Reminder> remindersDataset;

  // Provide a suitable constructor (depends on the kind of dataset)
  public RemindersAdapter(Context context, List<Reminder> remindersDataset) {
    this.context = context;
    this.remindersDataset = remindersDataset;
  }

  // Provide a reference to the views for each data item
  // Complex data items may need more than one view per item, and
  // you provide access to all the views for a data item in a view holder
  public static class ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView textView;

    public ViewHolder(TextView textView) {
      super(textView);
      this.textView = textView;
    }

  }

  // Create new views (invoked by the layout manager)
  @Override
  public RemindersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // create a new view
    TextView tv = (TextView) LayoutInflater
      .from(context)
      .inflate(R.layout.my_text_view, parent, false);
    return new ViewHolder(tv);
  }

  // Replace the contents of a view (invoked by the layout manager)
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Reminder reminder = remindersDataset.get(position);
    holder.textView.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(reminder.getDateTime()) + " " + reminder.getNote());
    holder.textView.setOnClickListener(v -> {
      remindersDataset.remove(position);
      notifyItemRemoved(position);
      notifyItemRangeChanged(position, getItemCount());
      new ReminderFile(context, reminder.getId()).delete();
      new ReminderAlarm(context, reminder.getId()).cancel();
    });
  }

  // Return the size of your dataset (invoked by the layout manager)
  @Override
  public int getItemCount() {
    return remindersDataset.size();
  }

}