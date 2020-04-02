package com.example.messagingapp.models;

//@Entity(tableName = "message")
public class Message {

//    @PrimaryKey(autoGenerate = true)
    private int id;

//    @NonNull
//    @ColumnInfo(name = "sender")
    private String sender;
//    @NonNull
//    @ColumnInfo(name = "last_message")
    private String lastMessage;
//    @NonNull
//    @ColumnInfo(name = "timestamp")
    private String timestamp;

    public Message() {
    }

    public Message(int id, String sender, String lastMessage, String timestamp) {
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
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
