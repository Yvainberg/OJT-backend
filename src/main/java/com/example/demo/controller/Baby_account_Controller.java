package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.*;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.example.demo.functions.ResponseHandler;
import com.example.demo.models.Baby_account;

import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/baby_account")
public class Baby_account_Controller {

@Autowired
 private Baby_account_Service Baby_account_Service;

@PostMapping("/create")
public ResponseEntity create_Baby_account(@RequestBody Baby_account Baby_account) {
   try {
        return new ResponseHandler( Baby_account_Service.create_Baby_account(Baby_account), 200, null); 
    
    } catch (Exception e) {
        return new ResponseHandler( null, 400, "error "); 
    }
    
}
@PutMapping("/delete")
    public ResponseEntity delete_Baby_account(@RequestBody  Baby_account Baby_account) {
        System.out.println("ppppppppp");
        try {
            return  new ResponseHandler(Baby_account_Service.delete_Baby_account( Baby_account), 200, null);
        } catch (Exception e) {
            return new ResponseHandler( null, 400, "error"); 
        }
    
 }

@PutMapping("/discharge")
public ResponseEntity discharge( @RequestBody Baby_account Baby_account) {
    String str = Baby_account_Service.discharge(Baby_account);
   try {
        return new ResponseHandler( str, 400, "error"); 
    } 
    catch (Exception e) {
        return new ResponseHandler( null, 400, "error"); 
    }
    
}

@GetMapping("getPagesNum/{pageSize}")
public ResponseEntity getPagesNum(@PathVariable int pageSize) {
   
    try {
        Map map = Baby_account_Service.getPagesNum(pageSize);
       
        return new ResponseHandler(map, 200, null);
    } catch (Exception e) {
       
        return new ResponseHandler(e, 400, null);
    }
}

@GetMapping("getBaby_accountByPageNum/{currentPage}/{pageSize}")
public ResponseEntity getBaby_accountByPageNum(@PathVariable int currentPage, @PathVariable int pageSize) {
   
    try {
        List list = Baby_account_Service.getBaby_accountByPageNum(pageSize, currentPage);
       return new ResponseHandler(list, 200, null);
    } catch (Exception e) {
      
        return new ResponseHandler(e, 400, null);
    }
}


@GetMapping("getPagesNumOfbaby_accountsArchive/{pageSize}")
public ResponseEntity getPagesNumOfBaby_accountsArchive(@PathVariable int pageSize) {
   try {
        Map map = Baby_account_Service.getPagesNumOfBaby_accountsArchive(pageSize);
        return new ResponseHandler(map, 200, null);
    } catch (Exception e) {
        return new ResponseHandler(e, 400, null);
    } 
}

@GetMapping("getArchiveBaby_accountByPageNum/{currentPage}/{pageSize}")
    public ResponseEntity getArchiveBaby_accountByPageNum(@PathVariable int currentPage, @PathVariable int pageSize) {
    try {
        List list = Baby_account_Service.getArchiveBaby_accountByPageNum(pageSize, currentPage);
        return new ResponseHandler(list, 200, null);
    } catch (Exception e) {
        return new ResponseHandler(e, 400, null);
    } 
}


@PostMapping("getPagesNumOfSearchBaby_account")
public ResponseEntity getPagesNumOfSearchBaby_account(@RequestBody Map<String, String> jsonSearch) {
    System.out.println("oooooooooooooooooooooooooooooooh");
    try{
        Map object = jsonSearch;
        int size = Integer.parseInt((String)object.get("size"));
        String search = (String) object.get("search");

        return new ResponseHandler(Baby_account_Service.getPagesNumOfSearchBaby_account(size, search), 200, null);
    }catch(Exception exception){
        return new ResponseHandler(exception, 400, null);
    }
}

@PostMapping("getSearchBaby_accountByPageNum")
public ResponseEntity getSearchBaby_accountByPageNum(@RequestBody Map<String, String> jsonSearch) {
    try{
        Map object = jsonSearch;
        int size = Integer.parseInt((String)object.get("size"));
        String search = (String) object.get("search");
        int currentPage = Integer.parseInt((String)object.get("currentPage"));
      return new ResponseHandler(Baby_account_Service.getSearchBaby_accountByPageNum(size, search, currentPage), 200, null);
    } 
    catch(Exception exception){
        return new ResponseHandler(exception, 400, null);
    }
}
    
 }
