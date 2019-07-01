package com.example.roomproject;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {
  private   AppDatabase database;
  private final String DB_NAME="myContacts";

    public DatabaseClient(Context context) {
        database= Room.databaseBuilder(context,AppDatabase.class,DB_NAME).build();
    }

    public AppDatabase getAppDatabase(){
        return database;
    }
}
