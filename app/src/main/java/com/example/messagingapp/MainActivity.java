package com.example.messagingapp;
import android.Manifest;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.messagingapp.adapters.MessagesRecyclerAdapter;
import com.example.messagingapp.models.Message;
import com.example.messagingapp.persistence.MessageRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MessagesRecyclerAdapter.OnMessageListener, FloatingActionButton.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Message> mMessages = new ArrayList<>();
    private MessagesRecyclerAdapter messagesRecyclerAdapter;
    private MessageRepository messageRepository;
    private static final int PERMISSIONS_REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        findViewById(R.id.fab).setOnClickListener(this);
        initRecyclerView();
        messageRepository = new MessageRepository(this);
        retrieveMessages();
        setSupportActionBar((Toolbar)findViewById(R.id.message_toolbar));
        setTitle("Messaging App");
        checkForSmsPermission();
    }

    private void retrieveMessages() {
        messageRepository.retrieveMessageTask().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(@Nullable List<Message> messages) {
                if(mMessages.size() > 0){
                    mMessages.clear();
                }
                if(messages != null){
                    mMessages.addAll(messages);
                }
                messagesRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

//    private void insertMessages(){
//        for (int i=0; i<50; i++){
//            Message message = new Message();
//            message.setSender("sender #"+i);
//            message.setLastMessage("last was this #"+i);
//            message.setTimestamp("hsaj");
//            message.setId(i);
//            mMessages.add(message);
//        }
//        messagesRecyclerAdapter.notifyDataSetChanged();
//    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        messagesRecyclerAdapter = new MessagesRecyclerAdapter(mMessages, this);
        recyclerView.setAdapter(messagesRecyclerAdapter);
    }

    @Override
    public void onMessageClick(int position){
        Intent intent = new Intent(this, ConversationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, NewMessageActivity.class);
        startActivity(intent);
    }

    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_SEND_SMS: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.SEND_SMS) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissions granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
