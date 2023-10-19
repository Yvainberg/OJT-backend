package com.example.demo.models;

import java.sql.Date;

import javax.xml.crypto.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "baby_metrics")
public class Baby_metrics {

    @Id
    int id;
    int weight;
    int size;
    int o2_saturation;
    int heart_rate;
    int temperature;
    int baby_id;
    String comments;
    Date date;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getO2_saturation() {
        return o2_saturation;
    }

    public void setO2_saturation(int o2_saturation) {
        this.o2_saturation = o2_saturation;
    }

    public  int getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate) {
        this.heart_rate = heart_rate;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getBaby_id() {
        return baby_id;
    }

    public void setBaby_id(int baby_id) {
        this.baby_id = baby_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
