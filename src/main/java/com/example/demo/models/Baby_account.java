package com.example.demo.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "baby_acount")
public class Baby_account {

    @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    String name;
    Integer speaker_id;
    int parent_id;
    int gestational_age_date;
    Date birth_date;
    String sex;
    Boolean is_active=true;
    String id_num;

    public String getname() {
        return name;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSpeaker_id() {
        return speaker_id;
    }
    public void setSpeaker_id(int speaker_id) {
        this.speaker_id = speaker_id;
    }
    public int getParent_id() {
        return parent_id;
    }
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
    public int getGestational_age_date() {
        return gestational_age_date;
    }
    public void setGestational_age_date(int gestational_age_date) {
        this.gestational_age_date = gestational_age_date;
    }
    public Date getBirth_date() {
        return birth_date;
    }
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Boolean getIs_active() {
        return is_active;
    }
    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
    public String getId_num() {
        return id_num;
    }
    public void setId_num(String id_num) {
        this.id_num = id_num;
    }
}
