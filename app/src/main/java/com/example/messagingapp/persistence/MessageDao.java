package com.example.messagingapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.messagingapp.models.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert
    long[] insertMessages(Message...messages);

    @Query("SELECT * FROM message")
    LiveData<List<Message>> getMessages();

    @Delete
    int delete(Message...messages);
}
