package com.example.demo.models;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;


interface Repository_Hospital extends JpaRepository<Hospital, Integer>{
    boolean existsByName(String name);
}

@Service
public class Hospital_Model {

    @Autowired
    Repository_Hospital repository_Hospital;


    public List<Hospital> findAll() {
        return repository_Hospital.findAll();
    }

    public Optional<Hospital> findById(int index) {
        return repository_Hospital.findById(index);
    }

    public boolean isNameExists(String name) {
        return repository_Hospital.existsByName(name);
    }

    public Hospital saveHospital(Hospital hospital) {
        return repository_Hospital.save(hospital);
    }

// test not for work
    public Hospital create(Hospital hospital)throws Exception{
        try {
        System.out.println("hospital model is run");
          return repository_Hospital.save(hospital);
        } catch (Exception e) {
            System.out.println("hospital model not  run");
            throw e;
        }
      }
    
}
