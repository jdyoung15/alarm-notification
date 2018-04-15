package com.example.alarmnotification.io;

import android.content.Context;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by jeremy on 3/29/18.
 */

public class ObjectFile {

  private Context context;
  private String filename;

  public ObjectFile(Context context, String filename) {
    this.context = context;
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }

  public void write(Object object) {
    try (ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput(filename, Context.MODE_PRIVATE))) {
      oos.writeObject(object);
    }
    catch (Exception e) {
      // TODO: handle this differently?
      e.printStackTrace();
    }
  }

  public Object read() {
    Object object = null;
    try (ObjectInputStream ois = new ObjectInputStream(context.openFileInput(filename))) {
      object = ois.readObject();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return object;
  }

  public void delete() {
    context.deleteFile(filename);
  }

}
