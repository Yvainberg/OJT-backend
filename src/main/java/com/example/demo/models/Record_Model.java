package com.example.demo.models;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;


interface Repository_Record extends JpaRepository<Record, Integer>{}

@Service
public class Record_Model {
    
    @Autowired
    Repository_Record repository_Record;

    public List<Record> findAll(){
        return repository_Record.findAll();
    }
    
    public Record findById(int index){
        try{
            return repository_Record.findById(index).get();
        }
        catch(Exception e){
            return null;
        }
    }
    
}
