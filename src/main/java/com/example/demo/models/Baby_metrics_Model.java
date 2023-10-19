package com.example.demo.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


interface Repository_Baby_metrics extends JpaRepository<Baby_metrics, Integer>{
    boolean existsByid (Integer baby_id);
   
}

    @Service
    public class Baby_metrics_Model {

    
    
    @Autowired
    Repository_Baby_metrics Repository_Baby_metrics;

    
     public boolean checkBaby_id(int baby_id){
         return Repository_Baby_metrics.existsByid(baby_id);
     }

    public String createMetrics(Baby_metrics babyMetrics){
         Integer id=babyMetrics.getBaby_id();
        Repository_Baby_metrics.save(babyMetrics);
         babyMetrics.setBaby_id(id);
        return "babyMetrics create successfully";
    }

    public List<Baby_metrics> findAll(){
        
        return Repository_Baby_metrics.findAll();
    }
    
    public Baby_metrics findById(int index){
        try{
            return Repository_Baby_metrics.findById(index).get();
        }
        catch(Exception e){
            return null;
        }
    }

}
