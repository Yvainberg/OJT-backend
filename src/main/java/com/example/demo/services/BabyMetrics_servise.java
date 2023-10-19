package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.functions.ApiFunctions;
import com.example.demo.functions.ResponseHandler;
import com.example.demo.functions.SearchEntity;
import com.example.demo.models.Baby_metrics;
import com.example.demo.models.Baby_metrics_Model;
import com.example.demo.models.Hospital;
import com.example.demo.models.Speaker;
import com.example.demo.models.Speaker_Model;

@Service
public class BabyMetrics_servise {
    @Autowired
    Baby_metrics_Model BabyMetricsModel;

    @Autowired
    SearchEntity searchEntity;

    public String createMetrics(Baby_metrics babyMetrics) {
        if (babyMetrics.getBaby_id() == 0 || babyMetrics.getWeight() == 0 || babyMetrics.getSize() == 0
                || babyMetrics.getO2_saturation() == 0 || babyMetrics.getTemperature() == 0
                || babyMetrics.getHeart_rate() == 0 || babyMetrics.getComments() == null
                || babyMetrics.getDate() == null) {
                    
            return "Error One of the fields is empty ";
        }
        boolean check = BabyMetricsModel.checkBaby_id(babyMetrics.getBaby_id());
        if (!check) {
            return "error A baby_id isnot found";
        }

        else {
            return BabyMetricsModel.createMetrics(babyMetrics);
        }

    }

    public ResponseEntity getSpeakerByNamPage(int size, int currentPage) {
        List listSpeaker = BabyMetricsModel.findAll();
        List list = ApiFunctions.getNumObjectsByNumPage(size, currentPage, listSpeaker);
        return new ResponseHandler(list, 200,"error");
    }

    public ResponseEntity getPagesSum(int pageSize) {
        List list = BabyMetricsModel.findAll();
        int pagesSum = ApiFunctions.pageSize(pageSize, list);

        List list2 = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, list);

        Map map = new HashMap<>();
        map.put("SumPages", pagesSum);
        map.put("data", list2);
        return new ResponseHandler(map, 200,"error");
    }
    // public ResponseEntity getPagesNum(int pageSize) {
       
    //     List list = BabyMetricsModel.findAll();
        
    //     int PagesNum = ApiFunctions.pageSize(pageSize, list);
    //     List completedList = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, list);

    //     Map map = new HashMap<>();
    //     map.put("SumPages", PagesNum);
    //     map.put("data", completedList);
    //     return new ResponseHandler(map, 200,"error");
    // }
    
    // public List getBaby_accountByPageNum(int size, int currentPage) {
    //     List list_Baby_account = BabyMetricsModel.findAll();
    //     List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, list_Baby_account);
    //     return completedList;
    // }


//     public Map getPagesNumOfBaby_accountsArchive(int pageSize) {
//         List list = BabyMetricsModel.findAll();
//         List filteringList = (List)list.stream().filter(s -> ((Baby_account) s).getIs_active() == true).collect(Collectors.toList());
//         int PagesNum = ApiFunctions.pageSize(pageSize, filteringList);
//         List completedList = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, filteringList);
//         Map map = new HashMap<>();
//         map.put("SumPages", PagesNum);
//         map.put("data", completedList);
//         return map;
//     }

//     public List getArchiveBaby_accountByPageNum(int size, int currentPage) {
//         List list = BabyMetricsModel.findAll();
//         List filteringList = (List)list.stream().filter(s -> ((Baby_account) s).getIs_active() == true).collect(Collectors.toList());
//         List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, filteringList);
//         return completedList;
//     }


//     public Map getPagesNumOfSearchBaby_account(int size, String search) {
//         // fidls to search only with type String 
//         String [] tableColumns =  new String[]{"sex", "id_num"};
//         // search in table
//         List completedList = searchEntity.matchSearchInTable(search, "Baby_account", tableColumns);
//         // return object' that consist- num page, and list Hospital
//         Map map = searchEntity.searchPattern(size, completedList);
//         return map;
// }

//     public List getSearchBaby_accountByPageNum(int size, String search, int currentPage) {
//     // fidls to search only with type String 
//     String [] tableColumns =  new String[]{"sex", "id_num"};
//     // search in table
//     List list = searchEntity.matchSearchInTable(search, " baby_account", tableColumns);
//     // return object' that consist- list Hospital 
//     List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, list);
//     return completedList;
// }


   
}