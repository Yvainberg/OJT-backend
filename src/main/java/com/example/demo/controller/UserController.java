package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import com.example.demo.functions.ResponseHandler;
import com.example.demo.functions.SearchEntity;
import com.example.demo.services.Users_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    Users_Service users_Service;

    @Autowired
    SearchEntity searchEntity;


    @GetMapping("getPagesNum/{pageSize}")
    public ResponseEntity getPagesNum(@PathVariable int pageSize) {
        try {
            Map map = users_Service.getPagesNum(pageSize);
            return new ResponseHandler(map, 200, null);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, null);
        }
    }

    @GetMapping("getByPageNum/{currentPage}/{pageSize}")
    public ResponseEntity getByPageNum(@PathVariable int currentPage, @PathVariable int pageSize) {
        try {
            List list =  users_Service.getByPageNum(pageSize, currentPage);
            return new ResponseHandler(list, 200, null);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, null);
        }
    }


    @GetMapping("getPagesNumOfArchive/{pageSize}")
    public ResponseEntity getPagesNumOfArchive(@PathVariable int pageSize) {
        try {
            Map map = users_Service.getPagesNumOfArchive(pageSize);
            return new ResponseHandler(map, 200, null);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, null);
        }
    }

    @GetMapping("getArchiveByPageNum/{currentPage}/{pageSize}")
    public ResponseEntity getArchiveByPageNum(@PathVariable int currentPage, @PathVariable int pageSize) {
        try {
            List list = users_Service.getArchiveByPageNum(pageSize, currentPage);
            return new ResponseHandler(list, 200, null);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, null);
        }
    }


    @PostMapping("getPagesNumOfSearch")
    public ResponseEntity getPagesNumOfSearch(@RequestBody Map<String, String> jsonSearch) {
        try{
            Map object = jsonSearch;

            int size = Integer.parseInt((String)object.get("size"));
            String search = (String) object.get("search");

            return new ResponseHandler(users_Service.getPagesNumOfSearch(size, search), 200, null);
        }catch(Exception exception){
            return new ResponseHandler(exception, 400, null);
        }
    }

    @PostMapping("getSearchByPageNum")
    public ResponseEntity getSearchByPageNum(@RequestBody Map<String, String> jsonSearch) {
        try{
            Map object = jsonSearch;

            int size = Integer.parseInt((String)object.get("size"));
            String search = (String) object.get("search");
            int currentPage = Integer.parseInt((String)object.get("currentPage"));

            return new ResponseHandler(users_Service.getSearchByPageNum(size, search, currentPage), 200, null);
        }catch(Exception exception){
            return new ResponseHandler(exception, 400, null);
        }
    }

    
    @PostMapping("getPagesNumOfArchiveAndSearch")
    public ResponseEntity getPagesNumOfArchiveAndSearch(@RequestBody Map<String, String> jsonSearch) {
        System.out.println("getPagesNumOfArchiveAndSearch");
        try{
            Map object = jsonSearch;

            int size = Integer.parseInt((String)object.get("size"));
            String search = (String) object.get("search");

            return new ResponseHandler(users_Service.getPagesNumOfArchiveAndSearch(size, search), 200, null);
        }catch(Exception exception){
            return new ResponseHandler(exception, 400, null);
        }
    }


    @PostMapping("getArchiveAndSearchByPageNum")
    public ResponseEntity getArchiveAndSearchByPageNum(@RequestBody Map<String, String> jsonSearch) {
        System.out.println("getArchiveAndSearchByPageNum");
        try{
            Map object = jsonSearch;

            int size = Integer.parseInt((String)object.get("size"));
            String search = (String) object.get("search");
            int currentPage = Integer.parseInt((String)object.get("currentPage"));

            return new ResponseHandler(users_Service.getArchiveAndSearchByPageNum(size, search, currentPage), 200, null);
        }catch(Exception exception){
            return new ResponseHandler(exception, 400, null);
        }
    }

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Map<String, Object> jsonSearch) {
        
        try{
            Map map = jsonSearch;

            return new ResponseHandler(users_Service.create(map), 200 , null);
        }catch(Exception exception){
            return new ResponseHandler(exception, 400, null);
        }
    }
    
    @PutMapping("update")
    public ResponseEntity update(@RequestBody Map<String, Object> jsonObject) {
        
        try{
            Map map = jsonObject;

            return new ResponseHandler(users_Service.update(map), 200 , null);
        }catch(Exception exception){
            return new ResponseHandler(exception, 400, null);
        }
    }
  
    @PutMapping("moveToArchieved")
    public ResponseEntity moveToArchieved(@RequestBody Map<String, Object> jsonObject) {
        
        try{
            Map map = jsonObject;

            return new ResponseHandler(users_Service.moveToArchieved(map), 200 , null);
        }catch(Exception exception){
            return new ResponseHandler(exception, 400, null);
        }
    }

}
