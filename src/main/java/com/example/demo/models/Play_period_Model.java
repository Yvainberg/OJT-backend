package com.example.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

import java.util.List;

@Transactional
interface Repository_Play_period extends JpaRepository<Play_period, Integer> {

  @Query("SELECT u FROM  Play_period u WHERE u.baby_acount_id = ?1")
  List<Play_period> findBybaby(Integer index);

  @Query("SELECT u FROM Play_period u WHERE u.baby_acount_id = ?1 AND (u.date = ?2 OR u.date IS NULL) ")
  List<Play_period> findBabyEndDate(Integer baby, Integer date);

  @Query("SELECT u FROM Play_period u WHERE u.hospital_id = ?1 AND u.baby_acount_id IS NULL")
  List<Play_period> findHospital(Integer hospital);

  @Modifying
  @Query("UPDATE Play_period u SET u.start_time = ?1 , u.end_time = ?2 WHERE u.baby_acount_id = ?3")
  void update(Integer start_time, Integer end_time, Integer baby_acount_id);

  @Modifying
  @Query("DELETE FROM  Play_period u WHERE u.baby_acount_id = ?1")
   void deletebaby_acount_id(Integer baby_acount_id);
}

@Service
public class Play_period_Model {

  @Autowired
  Repository_Play_period repository_play_period;

  public List<Play_period> findAll() throws Exception {
    try {
      System.out.println("findall model");
      return repository_play_period.findAll();
    } catch (Exception e) {
      throw e;
    }

  }

  public Play_period findByID(Integer x) throws Exception {
    try {

      return repository_play_period.findById(x).get();
    } catch (Exception e) {
      System.out.println("eroor by find by id " + e);
      
      throw e;
    }
  }

  public List<Play_period> findByBabyEndDate(Integer baby, Integer today) throws Exception {
    try {
      System.out.println("findByBabyEndDate model");
      List<Play_period> list = repository_play_period.findBabyEndDate(baby, today);
      // Play_period a =new Play_period();
      // a.setHeader("A");
      // list.add(a);
      // System.out.println("list =="+ list.get(1).getHeader());
      return list;
    } catch (Exception e) {
      // System.out.println("eroor by find by id " + e);
      // throw e;
      return null;
    }
  }

  public List<Play_period> findByHospital(Integer hospital) {
    try {
      System.out.println("findHospital");
      return repository_play_period.findHospital(hospital);

    } catch (Exception e) {

      return null;
    }
  }

  public List<Play_period> findByBayb(Integer x) throws Exception {
    try {
      return repository_play_period.findBybaby(x);
    } catch (Exception e) {
      System.out.println("eroor by find by id " + e);
      throw e;
    }
  }

  public Play_period create(Play_period play_period) throws Exception {
    try {
      System.out.println("create seve");
      return repository_play_period.save(play_period);
    } catch (Exception e) {
      throw e;
    }
  }


  public void upDateDB(Integer start_time, Integer end_time, Integer baby_acount_id) throws Exception {
    try {
      System.out.println("upDate  model is run 1");
      repository_play_period.update(start_time, end_time, baby_acount_id);
    } catch (Exception e) {
      System.out.println("upDate  not run "+ e);
   
      throw e;
    }
  }
  
public void delete (Integer id)throws Exception{
  try {
    repository_play_period.deleteById(id);
  
  } catch (Exception e) {
    throw e;
  }
}

public void deleteByBabyAcount (Integer deletByBabyAcount)throws Exception{
  try {
    repository_play_period.deletebaby_acount_id(deletByBabyAcount);
  } catch (Exception e) {
    throw e;
  }
}

 

}
