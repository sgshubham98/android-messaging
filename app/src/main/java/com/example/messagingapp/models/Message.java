package com.example.messagingapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.messagingapp.util.Timestamp;

import java.util.Date;

@Entity(tableName = "message")
public class Message {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    @ColumnInfo(name = "sender")
    private String sender;

    @NonNull
    @ColumnInfo(name = "last_message")
    private String lastMessage;

    @NonNull
    @ColumnInfo(name = "timestamp")
    @TypeConverters({Timestamp.class})
    private Date timestamp;

    @Ignore
    public Message() {
    }

    public Message(int id, String sender, String lastMessage, Date timestamp) {
        this.id = id;
        this.sender = sender;
        this.lastMessage = lastMessage;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
