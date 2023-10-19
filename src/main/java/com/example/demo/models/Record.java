package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "records")
public class Record{

    @Id
    int id;
    int source_user_id;
    String link;
    String upload_date;
    String record_name;
  

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSource_user_id() {
        return source_user_id;
    }
    public void setSource_user_id(int source_user_id) {
        this.source_user_id = source_user_id;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getUpload_date() {
        return upload_date;
    }
    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }
    public String getRecord_name() {
        return record_name;
    }
    public void setRecord_name(String record_name) {
        this.record_name = record_name;
    }

}