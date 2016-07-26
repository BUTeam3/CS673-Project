package com.buteam3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

/** Channel class is an object representing a connection between a task
 * and a chat channel. 
 * 
 * @author buteam3
 */

@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long channelid;
	//Required fields
    @NotEmpty
    private String channelname;
    private int taskid;

    /**
     * Returns channelName
     * 
     * @return channelName as string  
     */
    public String getChannelName() {
        return channelname;
    }
    /**
     * Set channelname
     * 
     * @param channelname as string
     */
    public void setChannelName(String ChannelName) {
        this.channelname = ChannelName;
    }
    /**
     * Returns taskid
     * 
     * @return taskid as int  
     */
    public int getTaskId() {
        return taskid;
    }
    /**
     * Set taskid
     * 
     * @param taskid as int
     */
    public void setTaskId(int TaskId) {
        this.taskid = TaskId;
    }
    /**
     * Returns channelid
     * 
     * @return channelid as long  
     */
    public long getChannelId() {
        return channelid;
    }
}