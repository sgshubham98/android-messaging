package com.example.messagingapp.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.messagingapp.async.DeleteAsyncTask;
import com.example.messagingapp.async.InsertAsyncTask;
import com.example.messagingapp.models.Message;

import java.util.List;

public class MessageRepository {
    private MessageDatabase messageDatabase;

    public MessageRepository(Context context) {
        messageDatabase = MessageDatabase.getInstance(context);
    }

    public void insertMessageTask(Message message){
        new InsertAsyncTask(messageDatabase.getMessageDao()).execute(message);
    }

    public LiveData<List<Message>> retrieveMessageTask(){
        return messageDatabase.getMessageDao().getMessages();
    }

    public void deleteMessageTask(Message message){
        new DeleteAsyncTask(messageDatabase.getMessageDao()).execute(message);
    }
}
