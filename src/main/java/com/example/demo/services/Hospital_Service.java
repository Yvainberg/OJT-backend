package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.functions.ApiFunctions;
import com.example.demo.functions.ResponseHandler;
import com.example.demo.functions.SearchEntity;
import com.example.demo.models.Hospital;
import com.example.demo.models.Hospital_Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import jakarta.validation.*;
import java.util.*;

@Service
public class Hospital_Service {

   


   @Autowired
   Hospital_Model hospital_Model;


   @Autowired
   SearchEntity searchEntity;

   public Map getPagesNum(int pageSize) {
      List list = hospital_Model.findAll();

      int PagesNum = ApiFunctions.pageSize(pageSize, list);
      List completedList = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, list);

      Map map = new HashMap<>();
     
      map.put("SumPages", PagesNum);
      map.put("data", completedList);
     
      return map;
   }

   public List getByPageNum(int size, int currentPage) {
      List list = hospital_Model.findAll();
      List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, list);
      return completedList;
   }

   public Map getPagesNumOfArchive(int pageSize) {
      List list = hospital_Model.findAll();

      List filteringList = (List) list.stream().filter(s -> ((Hospital) s).getIs_active() == true)
            .collect(Collectors.toList());
      int PagesNum = ApiFunctions.pageSize(pageSize, filteringList);

      List completedList = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, filteringList);
      Map map = new HashMap<>();
      map.put("SumPages", PagesNum);
      map.put("data", completedList);
      return map;
   }

   public List getArchiveByPageNum(int size, int currentPage) {
      List list = hospital_Model.findAll();

      List filteringList = (List) list.stream().filter(s -> ((Hospital) s).getIs_active() == true)
            .collect(Collectors.toList());
      List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, filteringList);
      return completedList;
   }

   public Map getPagesNumOfSearch(int size, String search) {
      // fidls to search only with type String
      String[] tableColumns = new String[] { "name", "telephone", "address" };
      // search in table
      List completedList = searchEntity.matchSearchInTable(search, "Hospital", tableColumns);
      // return object' that consist- num page, and list Hospital
      Map map = searchEntity.searchPattern(size, completedList);
      return map;
   }

   public List getSearchByPageNum(int size, String search, int currentPage) {
      // fidls to search only with type String
      String[] tableColumns = new String[] { "name", "telephone", "address" };
      // search in table
      List list = searchEntity.matchSearchInTable(search, "Hospital", tableColumns);
      // return object' that consist- list Hospital
      List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, list);
      return completedList;
   }

   public Map getPagesNumOfArchiveAndSearch(int size, String search) {
      // fidls to search only with type String
      String[] tableColumns = new String[] { "name", "telephone", "address" };
      // search in table
      List completedList = searchEntity.matchSearchInTable(search, "Hospital", tableColumns);

      List filteringList = (List) completedList.stream().filter(s -> ((Hospital) s).getIs_active() == true)
            .collect(Collectors.toList());

      // return object' that consist- num page, and list Hospital
      Map map = searchEntity.searchPattern(size, filteringList);
      return map;
   }

   public List getArchiveAndSearchByPageNum(int size, String search, int currentPage) {
      // fidls to search only with type String
      String[] tableColumns = new String[] { "name", "telephone", "address" };
      // search in table
      List list = searchEntity.matchSearchInTable(search, "Hospital", tableColumns);
      // return object' that consist- list Hospital

      List filteringList = (List) list.stream().filter(s -> ((Hospital) s).getIs_active() == true)
            .collect(Collectors.toList());

      List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, filteringList);
      return completedList;
   }

   // this function creates new hospital, checks if name exists, if not edits
   // isActive = true and saves it with message
   public ResponseEntity createHospital(Hospital hospital) {
      boolean isNameExists = hospital_Model.isNameExists(hospital.getName());
      // this lines checks if there are errors in body, if there are errors will be
      // messages per error by lop for, if if there are not errors creates hospital
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();
      Set<ConstraintViolation<Hospital>> violations = validator.validate(hospital);
      List<String> list = new ArrayList<>();
      for (ConstraintViolation<Hospital> violation : violations) {
         list.add(violation.getMessage());
      }
      if (!list.isEmpty()) {
         return new ResponseHandler(list, 200, "error");
      } else if (isNameExists) {
         return new ResponseHandler(List.of("Error, name already exist"), 200, "error");
      } else {
         hospital.setIs_active(true);
         hospital_Model.saveHospital(hospital);
         return new ResponseHandler(List.of("created new hospital"), 200, null);
      }

   }

   // this function updates exists hospital by id, checks if id exists, if yes
   // checks if name exists, if not saves it with message
   public ResponseEntity updateHospital(Integer id, Hospital hospital) {
      // this lines checks if there are errors in body, if there are errors will be
      // messages per error by lop for, if there are not errors updates hospital
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();
      Set<ConstraintViolation<Hospital>> violations = validator.validate(hospital);
      List<String> list = new ArrayList<>();
      for (ConstraintViolation<Hospital> violation : violations) {
         list.add(violation.getMessage());
      }
      if (!list.isEmpty()) {
         return new ResponseHandler(list, 200, "error");
      }
      // if !hospitalOptional.isPresent() it says that there is not this id
      Optional<Hospital> hospitalOP = hospital_Model.findById(id);
      if (!hospitalOP.isPresent()) {
         return new ResponseHandler(List.of("Error, id does not exist"), 200, "error");
      }
      Hospital hospitalDB = hospitalOP.get();
      // so that it will not be disqualified because the name exists even it is the
      // original name
      boolean currentName = hospitalDB != null && hospitalDB.getName().equals(hospitalDB.getName());
      boolean isNameExists = hospital_Model.isNameExists(hospitalDB.getName());
      if (isNameExists && !currentName) {
         return new ResponseHandler(List.of("Error, name already exists"), 200, "error");
      } else {
         hospitalDB.setId(id);
         hospitalDB.setIs_active(true);
         hospital_Model.saveHospital(hospitalDB);
         return new ResponseHandler(List.of("updated hospital"), 200, null);
      }
   }

   // --test dont maerg--//

   public Hospital createHospitalDB(Map<String, Object> jsonBody) throws Exception{
      try {
         System.out.println("hospital sevice 1");
         Hospital hospital = new Hospital();
         hospital.setId((Integer)jsonBody.get("id"));
         hospital.setManeger_id((Integer)jsonBody.get("maneger_id"));
         hospital.setName((String)jsonBody.get("name"));
         hospital.setTelephone((String)jsonBody.get("telephone"));
         hospital.setAddress((String)jsonBody.get("address"));
         hospital.setIs_active((Boolean)jsonBody.get("is_active"));
         System.out.println("hospital sevice 2");

          return hospital_Model.create(hospital);
      } catch (Exception e) {
         throw e;
      }
  }

}
