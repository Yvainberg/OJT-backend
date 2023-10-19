package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.functions.ApiFunctions;
import com.example.demo.functions.ResponseHandler;
import com.example.demo.models.Speaker;
import com.example.demo.models.Speaker_Model;



@Service
public class Speaker_Service {

    @Autowired
    Speaker_Model speaker_Model;

    public static ResponseEntity<Object> responseBuiIderForPages(Object object, HttpStatus httpStatus, int SumPages) {
        Map map = new HashMap<>();
        map.put("SumPages", SumPages);
        map.put("data", object);
        return new ResponseEntity<>(map, httpStatus);
    }

    public static ResponseEntity<Object> responseBuiIder(Object object, HttpStatus httpStatus) {
        return new ResponseEntity<>(object, httpStatus);
    }


    public ResponseEntity getPagesSum(int pageSize) {
        List list = speaker_Model.findAll();
        int pagesSum = ApiFunctions.pageSize(pageSize, list);

        List list2 = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, list);

        Map map = new HashMap<>();
        map.put("SumPages", pagesSum);
        map.put("data", list2);
        return new ResponseHandler(map, 200,"error");
    }


    public ResponseEntity getSpeakerByNamPage(int size, int currentPage) {
        List listSpeaker = speaker_Model.findAll();
        List list = ApiFunctions.getNumObjectsByNumPage(size, currentPage, listSpeaker);
        return new ResponseHandler(list, 200,"error");
    }

    public List<Speaker> getAllUsers() {
        return speaker_Model.findAll();
    }

    public Speaker getUserById(int index) {
        return speaker_Model.findById(index);
    }

    public String createSpeaker(Speaker Speaker) {
        if (Speaker.getLast_session_time()==null || Speaker.getStart_working_date()==null){
            return "One of the fields is incomplete";
        }
       
            return speaker_Model.createSpeaker(Speaker);
      
    }
    
    public String updateSpeaker( Speaker speaker) {
        boolean check=speaker_Model.checkSpeaker(speaker);
        if(!check){
            return "error A speaker isnot found";
        }
        else {
            return speaker_Model.updateSpeaker(speaker);
        }
    }
    
    public List temp() {
        return speaker_Model.tempSearch();
    }


}
