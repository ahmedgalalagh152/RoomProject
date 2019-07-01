package com.example.roomproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Contact.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DatabaseDAO databaseDAO();
}
