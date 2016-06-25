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
    private String message;
	private String username;
	private int channel_id;
	private String timestamp;
	

    public String getData() {
        return message;
    }

    public void setData(String message) {
        this.message = message;
    }

}