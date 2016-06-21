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
    private int state;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}