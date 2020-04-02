package com.example.messagingapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.messagingapp.adapters.MessagesRecyclerAdapter;
import com.example.messagingapp.models.Message;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MessagesRecyclerAdapter.OnMessageListener{

    private RecyclerView recyclerView;
    private ArrayList<Message> messages = new ArrayList<>();
    private MessagesRecyclerAdapter messagesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =findViewById(R.id.recyclerView);
        initRecyclerView();
        insertMessages();
    }

    private void insertMessages(){
        for (int i=0; i<50; i++){
            Message message = new Message();
            message.setSender("sender #"+i);
            message.setLastMessage("last was this #"+i);
            message.setTimestamp("hsaj");
            message.setId(i);
            messages.add(message);
        }
        messagesRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        messagesRecyclerAdapter = new MessagesRecyclerAdapter(messages, this);
        recyclerView.setAdapter(messagesRecyclerAdapter);
    }

    @Override
    public void onMessageClick(int position){
        Intent intent = new Intent(this, Conversation.class);
        startActivity(intent);
    }
}
