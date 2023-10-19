package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.functions.ResponseHandler;
import com.example.demo.models.Speaker;
import com.example.demo.services.Speaker_Service;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("speaker")
public class SpeakerController {
    
    @Autowired
    Speaker_Service Speaker_Service;
   
    @PostMapping("/")
    public String createSpeaker(@RequestBody  @Validated Speaker speaker) {
         
        try {
            return Speaker_Service.createSpeaker(speaker);
        } catch (Exception e) {
            return "error"; 
        }
        
    }


    @PutMapping("/")
    public String updateSpeaker( @RequestBody Speaker speaker) {
       try {
            return Speaker_Service.updateSpeaker(speaker);
        } 
        catch (Exception e) {
            return "error";
        }
        
       
    }

    @GetMapping("getAll/{currentPage}/{pageSize}")
    public ResponseEntity getAll(@PathVariable int currentPage, @PathVariable int pageSize) {
        return Speaker_Service.getSpeakerByNamPage(pageSize, currentPage);
    }

    @GetMapping("getSumPage/{pageSize}")
    public ResponseEntity getSumPage(@PathVariable int pageSize) {
        return Speaker_Service.getPagesSum(pageSize);
    }

    @GetMapping("temp")
    public ResponseEntity temp() {
        try {
            return new ResponseHandler(Speaker_Service.temp(), 200, null);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, null);
        }
    }


}