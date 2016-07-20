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
    @NotEmpty
    private String channelname;
    private int taskid;

    public String getChannelName() {
        return channelname;
    }
    public void setChannelName(String ChannelName) {
        this.channelname = ChannelName;
    }
    public int getTaskId() {
        return taskid;
    }
    public void setTaskId(int TaskId) {
        this.taskid = TaskId;
    }
    public long getChannelId() {
        return channelid;
    }
}