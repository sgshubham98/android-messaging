package com.example.messagingapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.messagingapp.util.Timestamp;

import java.util.Date;

@Entity(tableName = "conversation")
public class Conversation {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    @ColumnInfo(name = "number")
    private String number;

    @NonNull
    @ColumnInfo(name = "message")
    private String message;

    @TypeConverters({Timestamp.class})
    private Date timestamp;

    public Conversation(int id, String number, String message, Date timestamp) {
        this.id = id;
        this.number = number;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
