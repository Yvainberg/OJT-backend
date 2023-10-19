package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;



@Entity
@Table(name = "hospital")
public class Hospital{

    @Id
    int id;
    @NotNull(message = "Error, maneger id is required 1")
    Integer maneger_id;
    @NotEmpty(message = "Error, name is required 2")
    String name;
    @NotEmpty(message = "Error, telephone is required 3")
    String telephone;
    @NotEmpty(message = "Error, address is required 4")
    String address;
    Boolean is_active;

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getManeger_id() {
        return maneger_id;
    }
    public void setManeger_id(int maneger_id) {
        this.maneger_id = maneger_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Boolean getIs_active() {
        return is_active;
    }
    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

}
