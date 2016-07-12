package com.buteam3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

/** Message class is an object representing a message within
 * a chat channel. 
 * 
 * @author buteam3
 */

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long mid;
    @NotEmpty
    private String data;
    private int channelid;
    //private String timestamp;
    private String username;

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String Username) {
        this.username = Username;
    }

}