package com.example.demo.models;

import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "play_period")
@Service
public class Play_period {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "id", allocationSize = 1)
    int id;

    Integer hospital_id;
    Integer baby_acount_id;
    Integer date;
    Integer start_time;
    Integer end_time;

    @Transient
    String playbackType;
    
    

   
    public String getPlaybackType() {
        return playbackType;
    }
    public void setPlaybackType(String playbackType) {
        this.playbackType = playbackType;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Integer getHospital_id() {
        return hospital_id;
    }
    public void setHospital_id(Integer hospital_id) {
        this.hospital_id = hospital_id;
    }
    public Integer getBaby_acount_id() {
        return baby_acount_id;
    }
    public void setBaby_acount_id(Integer baby_acount_id) {
        this.baby_acount_id = baby_acount_id;
    }
    public Integer getDate() {
        return date;
    }
    public void setDate(Integer date) {
        this.date = date;
    }
    public Integer getStart_time() {
        return start_time;
    }
    public void setStart_time(Integer start_time) {
        this.start_time = start_time;
    }
    public Integer getEnd_time() {
        return end_time;
    }
    public void setEnd_time(Integer end_time) {
        this.end_time = end_time;
    }
   

}
