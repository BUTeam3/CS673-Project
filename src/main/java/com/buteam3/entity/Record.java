package com.buteam3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    @NotEmpty
    private String data;
	private String timestamp;
	//private int difficulty;
    private int state;
    
    
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
     * @param data text data of task
     */
    public void setData(String data) {
        this.data = data;
    }
    /**
     * returns timestamp of task
     * 
     * @return timestamp of task as a string
     */
    public String getTimestamp() {
        return timestamp;
    }
    /**
     * Set timestamp of the task
     * 
     * @param Timestamp string representing new timestamp
     */
    public void setTimestamp(String Timestamp) {
        this.timestamp = Timestamp;
    }
 /*   public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int Difficulty) {
        this.difficulty = Difficulty;
    }
  */
    /**
     * Set state of task(is it in icebox, backlog etc.)
     * 
     * @param State integer representing state of task
     */
    public void setState(int State) {
        this.state = State;
    }

}