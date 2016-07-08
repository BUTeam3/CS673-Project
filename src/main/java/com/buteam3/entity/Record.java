package com.buteam3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @NotEmpty
    private String data;
	private String timestamp;
    private int state;
	//private int difficulty;

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
    public int getState() {
        return state;
    }
	public void setState(int State) {
        this.state = State;
    }
 /*   public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int Difficulty) {
        this.difficulty = Difficulty;
    }
  */
}