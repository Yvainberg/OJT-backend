package com.example.demo.models;

import java.sql.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "speaker")
public class Speaker {
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;
    int hospital_id;
    Date start_working_date;
    Date last_session_time;


    // public Speaker(Speaker speaker) {
    // }
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getHospital_id() {
        return hospital_id;
    }
    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }
    public Date getStart_working_date() {
        return start_working_date;
    }
    public void setStart_working_date(Date start_working_date) {
        this.start_working_date = start_working_date;
    }
    public Date getLast_session_time() {
        return last_session_time;
    }
    public void setLast_session_time(Date last_session_time) {
        this.last_session_time = last_session_time;
    }
}
