package com.example.demo.services;

import java.lang.annotation.Retention;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;

import com.example.demo.models.Play_period;
import com.example.demo.models.Play_period_Model;

@Service
public class Play_period_Service {

    @Autowired
    Play_period_Model play_period_Model;

    @Autowired
    Play_period Play_period;

    // --test function-- //
    public List findAllDB() throws Exception {

        List list = play_period_Model.findAll();

        return list;
    }

    public Play_period findByID(Map<String, Integer> index) throws Exception {
        Integer id = index.get("id");
        return play_period_Model.findByID(id);

    }

    public List<Play_period> findhospital1(Map<String, Integer> hospital) throws Exception {
        Integer hospitalID = hospital.get("hospital_id");

        return play_period_Model.findByHospital(hospitalID);
    }

    public List<Play_period> findBaby(Map<String, Integer> index) throws Exception {
        Integer baby_acount_id = index.get("baby_acount_id");
        return play_period_Model.findByBayb(baby_acount_id);

    }

    public List<Play_period> findbyBabyEndDate(Map<String, Integer> baby) throws Exception {
        Integer baby_acount_id = baby.get("baby_acount_id");
        Integer date = baby.get("date");
        return play_period_Model.findByBabyEndDate(baby_acount_id, date);

    }

    public Play_period create(Map<String, Integer> jsonBody) throws Exception {
        try {

            // Play_period Play_period = new Play_period();
            Play_period.setHospital_id(jsonBody.get("hospital_id"));
            Play_period.setDate(jsonBody.get("date"));
            Play_period.setStart_time(jsonBody.get("start_time"));
            Play_period.setEnd_time(jsonBody.get("end_time"));
            Play_period.setBaby_acount_id(jsonBody.get("baby_acount_id"));

            System.out.println(" create service ==>  " + Play_period.getHospital_id());
            return play_period_Model.create(Play_period);
        } catch (Exception e) {
            return null;
        }
    }

    public void upDate(Map<String, Integer> information) throws Exception {
        System.out.println("upDate  service is run 1");

        Integer start_time = information.get("start_time");
        Integer end_time = information.get("end_time");
        Integer baby_acount_id = information.get("baby_acount_id");

        System.out.println("upDate service is run 2  >" + baby_acount_id + " >" + end_time + " >" + start_time);
        play_period_Model.upDateDB(start_time, end_time, baby_acount_id);
    }

    public List<Play_period> playTimes(Map<String, Integer> playTimes) throws Exception {
        Integer baby_acount_id = playTimes.get("baby_acount_id");

        LocalDate today = LocalDate.now();
        // today.toEpochSecond(java.time.ZoneOffset.UTC);

        // LocalDate today = LocalDate.now();
        // long timestamp = today.atStartOfDay(ZoneOffset.UTC).toInstant().getEpochSecond();
        // System.out.println(timestamp);

        System.out.println(today);
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        int dateAsInt = year * 10000 + month * 100 + day;

        List exsists = play_period_Model.findByBabyEndDate(baby_acount_id, dateAsInt);
        System.out.println("exsists =>" + exsists.isEmpty());
        if (exsists.isEmpty()) {

            Integer hospital_id = playTimes.get("hospital_id");

            return play_period_Model.findByHospital(hospital_id);
        } else {

            return exsists;
        }
    }

    public void delete(Map<String, Integer> id) throws Exception {
        Integer deletByID = id.get("id");
        play_period_Model.delete(deletByID);
    }

    public void deleteBabyAcont(Map<String, Integer> id) throws Exception {
        try {
            Integer deletByBabyAcount = id.get("baby_acount_id");
            System.out.println("baby_acount_id ==>  " + deletByBabyAcount);
            play_period_Model.deleteByBabyAcount(deletByBabyAcount);
        } catch (Exception e) {
            throw e;
        }

    }

}