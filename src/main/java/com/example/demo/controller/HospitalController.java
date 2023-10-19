package com.example.demo.controller;

import com.example.demo.functions.ResponseHandler;
import com.example.demo.services.Hospital_Service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Hospital;

@RestController
@RequestMapping("hospital")
public class HospitalController {

    @Autowired
    Hospital_Service hospital_Service;

    @GetMapping("getPagesNum/{pageSize}")
    public ResponseEntity getPagesNum(@PathVariable int pageSize) {
        try {
            Map map = hospital_Service.getPagesNum(pageSize);
            return new ResponseHandler(map, 200, null);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, null);
        }
    }

    @GetMapping("getByPageNum/{currentPage}/{pageSize}")
    public ResponseEntity getByPageNum(@PathVariable int currentPage, @PathVariable int pageSize) {
        try {
            List list = hospital_Service.getByPageNum(pageSize, currentPage);
            ;
            return new ResponseHandler(list, 200, null);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, null);
        }
    }

    @GetMapping("getPagesNumOfArchive/{pageSize}")
    public ResponseEntity getPagesNumOfArchive(@PathVariable int pageSize) {
        try {
            Map map = hospital_Service.getPagesNumOfArchive(pageSize);
            return new ResponseHandler(map, 200, null);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, null);
        }
    }

    @GetMapping("getArchiveByPageNum/{currentPage}/{pageSize}")
    public ResponseEntity getArchiveByPageNum(@PathVariable int currentPage, @PathVariable int pageSize) {
        try {
            List list = hospital_Service.getArchiveByPageNum(pageSize, currentPage);
            return new ResponseHandler(list, 200, null);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, null);
        }
    }

    @PostMapping("getPagesNumOfSearch")
    public ResponseEntity getPagesNumOfSearch(@RequestBody Map<String, String> jsonSearch) {
        try {
            Map object = jsonSearch;
            int size = Integer.parseInt((String) object.get("size"));
            String search = (String) object.get("search");

            return new ResponseHandler(hospital_Service.getPagesNumOfSearch(size, search), 200, null);
        } catch (Exception exception) {
            return new ResponseHandler(exception, 400, null);
        }
    }

    @PostMapping("getSearchByPageNum")
    public ResponseEntity getSearchByPageNum(@RequestBody Map<String, String> jsonSearch) {
        try {
            Map object = jsonSearch;
            int size = Integer.parseInt((String) object.get("size"));
            // System.out.println("size = " + size);
            String search = (String) object.get("search");
            // System.out.println("search = " + search);
            int currentPage = Integer.parseInt((String) object.get("currentPage"));
            // System.out.println("currentPage = " + currentPage);
            return new ResponseHandler(hospital_Service.getSearchByPageNum(size, search, currentPage), 200, null);
        } catch (Exception exception) {
            return new ResponseHandler(exception, 400, null);
        }
    }

    @PostMapping("getPagesNumOfArchiveAndSearch")
    public ResponseEntity getPagesNumOfArchiveAndSearch(@RequestBody Map<String, String> jsonSearch) {
        System.out.println("getPagesNumOfArchiveAndSearch");
        try {
            Map object = jsonSearch;

            int size = Integer.parseInt((String) object.get("size"));
            String search = (String) object.get("search");

            return new ResponseHandler(hospital_Service.getPagesNumOfArchiveAndSearch(size, search), 200, null);
        } catch (Exception exception) {
            return new ResponseHandler(exception, 400, null);
        }
    }

    @PostMapping("getArchiveAndSearchByPageNum")
    public ResponseEntity getArchiveAndSearchByPageNum(@RequestBody Map<String, String> jsonSearch) {
        System.out.println("getArchiveAndSearchByPageNum");
        try {
            Map object = jsonSearch;

            int size = Integer.parseInt((String) object.get("size"));
            String search = (String) object.get("search");
            int currentPage = Integer.parseInt((String) object.get("currentPage"));

            return new ResponseHandler(hospital_Service.getArchiveAndSearchByPageNum(size, search, currentPage), 200,
                    null);
        } catch (Exception exception) {
            return new ResponseHandler(exception, 400, null);
        }
    }

    @PostMapping()
    public ResponseEntity createHospital(@RequestBody Hospital hospital) {
        try {
            return hospital_Service.createHospital(hospital);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, "error");
        }
    }

    @PutMapping("/{hospital_id}")
    public ResponseEntity updateHospital(@PathVariable("hospital_id") Integer id, @RequestBody Hospital hospital) {
        try {
            return hospital_Service.updateHospital(id, hospital);
        } catch (Exception e) {
            return new ResponseHandler(e, 400, "error");
        }
    }

    // ---test dont seve --- //
    @PostMapping("createHospital")
    public Hospital create1(@RequestBody Map<String, Object> jsonSearch) {
        try {
                System.out.println("hospital controller is run");
            return hospital_Service.createHospitalDB(jsonSearch);

        } catch (Exception e) {
            System.out.println("hospital controller not run");
            return null;
        }

    }

}
