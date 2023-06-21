package com.example.eboy_backend_2.messages;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

    private String senderUsername;

    private String receiverUsername;

    private Boolean isRead;

    public Message() {}

    public Message(Integer id, String message, String senderUsername, String receiverUsername, Boolean isRead) {
        this.id = id;
        this.message = message;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.isRead = isRead;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public Boolean getIsRead() { return isRead; }

    public void setIsRead(Boolean isRead) { this.isRead = isRead; }
}
