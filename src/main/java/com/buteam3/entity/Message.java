package com.buteam3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long mid;
    @NotEmpty
    private String data;
    private int channelId;
	private String timestamp;
	private String username;

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String Timestamp) {
        this.timestamp = Timestamp;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String Username) {
        this.username = Username;
    }

}