package com.example.messagingapp.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.messagingapp.models.Conversation;
import com.example.messagingapp.models.Message;

@Database(entities = {Message.class, Conversation.class}, version = 1)
public abstract class MessageDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "message_app_db";
    private static MessageDatabase instance;

    static MessageDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MessageDatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract MessageDao getMessageDao();
}
