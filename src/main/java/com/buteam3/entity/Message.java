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
	//Required fields
    @NotEmpty
    private String data;
    private int channelid;
    //public String timestamp;
    private String username;
  
    /**
     * Returns text data for the message
     * 
     * @return text data as string  
     */
    public String getData() {
        return data;
    }
    /**
     * Set data for message
     * 
     * @param data as string
     */
    public void setData(String data) {
        this.data = data;
    }
    /**
     * Returns Username for the message
     * 
     * @return username as string  
     */
    public String getUsername() {
        return username;
    }
    /**
     * Set username for message
     * 
     * @param username as string
     */
    public void setUsername(String Username) {
        this.username = Username;
    }
    /**
     * Returns channelid for the message
     * 
     * @return channelid as int  
     */
    public int getChannelId() {
        return channelid;
    }
    /**
     * Set channelID for message
     * 
     * @param channelID as int
     */
    public void setChannelId(int ChannelId) {
        this.channelid = ChannelId;
    }

}