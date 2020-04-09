package com.example.messagingapp.async;

import android.os.AsyncTask;

import com.example.messagingapp.models.Message;
import com.example.messagingapp.persistence.MessageDao;

public class InsertAsyncTask extends AsyncTask<Message, Void, Void> {
    private MessageDao messageDao;
    public InsertAsyncTask(MessageDao dao){
        messageDao = dao;
    }

    @Override
    protected Void doInBackground(Message...messages){
        messageDao.insertMessages(messages);
        return null;
    }
}
