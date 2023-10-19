package com.example.demo.models;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


interface Repository_Speaker extends JpaRepository<Speaker, Integer>{
    boolean existsByid (Integer string);
    

}

    @Service
    public class Speaker_Model {

    
    
    @Autowired
    Repository_Speaker repository_Speaker;

    
    public boolean checkSpeaker(Speaker Speaker){
        return repository_Speaker.existsByid(Speaker.getId());
    }

    public String createSpeaker(Speaker speaker){
        repository_Speaker.save(speaker);
        return "Speaker create successfully";
    }

    public String updateSpeaker( Speaker speaker){
       
        Integer id=speaker.getId();
        repository_Speaker.save(speaker);
        speaker.setId(id);
        return "Speaker update successfully";
    }

    public List<Speaker> findAll(){
        return repository_Speaker.findAll();
    }
    
    public Speaker findById(int index){
        try{
            return repository_Speaker.findById(index).get();
        }
        catch(Exception e){
            return null;
        }
    }

    @PersistenceContext
    EntityManager entityManager;
    public List tempSearch(){
        String str = "FROM Speaker WHERE CONCAT(id, '') like '1%'";
        //String str = "FROM Speaker WHERE id = '1'";
        // (cast(:createdAt as timestamp)
        Query query = entityManager.createQuery(str);
        return query.getResultList();
        
    }

}
