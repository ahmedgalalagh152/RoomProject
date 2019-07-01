package com.example.roomproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DatabaseDAO  {

    @Insert
    void insertAll(Contact ... contacts);


    @Query("select * from contact")
    List<Contact> getAll();


    @Query("select * from contact where id = :id")
    Contact getContact (int id);


    @Update
    void  update(Contact contact);

    @Delete
    void delete(Contact contact);



}
