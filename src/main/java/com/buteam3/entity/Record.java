package com.buteam3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Record class is an object representing a task in project management tool.
 * 
 * 
 * @author buteam3, springboot framework provider
 */
@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
	private Long channelid;
//	private Timestamp timestamp;
	//Required fields
    @NotEmpty
    private String data;
    private int state;
	private int difficulty;  
    
    /**
     * Returns text data for the task
     * 
     * @return text data as string  
     */
    public String getData() {
        return data;
    }
    /**
     * Set text data for the task
     * 
     * @param String data
     */
    public void setData(String data) {
        this.data = data;
    }
    /**
     * returns timestamp of task
     * 
     * @return timestamp of task as a Timestamp
     */
/*    public Timestamp getTimestamp() {
      return timestamp;
    }
*/    /**
     * Set timestamp of the task
     * 
     * @param Timestamp timestamp
     */
/*    public void setTimestamp(Timestamp Timestamp) {
        this.timestamp = Timestamp;
    }
*/    /**
     * Set state of task(is it in icebox, backlog etc.)
     * 
     * @param State integer representing state of task
     */
    public void setState(int State) {
        this.state = State;
    }
    /**
     * returns state of task
     * 
     * @return State of task as an integer
     */
    public int getState() {
        return state;
    }
    /**
     * Set difficulty of task(is it in icebox, backlog etc.)
     * 
     * @param Difficulty integer representing difficulty of task
     */
    public void setDifficulty(int Difficulty) {
        this.difficulty = Difficulty;
    }
    /**
     * returns id of task
     * 
     * @return ID of task as a long
     */
	public long getId(){
		return id;
	}
    /**
     * Set channelId of task
     * 
     * @param ChannelId long representing channelId of task
     */
    public void setChannelId(long ChannelId) {
        this.channelid = ChannelId;
    }
    /**
     * returns channelId of task
     * 
     * @return channelId of task as a long
     */
    public long getChannelId() {
        return channelid;
    }	
}