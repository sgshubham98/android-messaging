package com.example.messagingapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.messagingapp.models.Conversation;
import com.example.messagingapp.models.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert
    void insertMessages(Message...messages);

    @Insert
    void insertConversation(Conversation...conversations);

    @Query("SELECT * FROM message Order By timestamp DESC")
    LiveData<List<Message>> getMessages();

    @Query("Select * from conversation where number = :contactNumber")
    LiveData<List<Conversation>> getConversation(String contactNumber);

    @Delete
    int delete(Message...messages);

}
