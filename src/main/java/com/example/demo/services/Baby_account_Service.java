package com.example.demo.services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.functions.ApiFunctions;
import com.example.demo.functions.SearchEntity;
import com.example.demo.models.Baby_account;
import com.example.demo.models.Baby_account_Model;

@Service
public class Baby_account_Service {
    
    @Autowired
    Baby_account_Model baby_account_Model;

    @Autowired
    SearchEntity searchEntity;
    
    public String create_Baby_account (Baby_account Baby_account){
        if(Baby_account.getBirth_date()==null || Baby_account.getGestational_age_date()==0|| Baby_account.getId_num()==null|| Baby_account.getParent_id()==0 || Baby_account.getSex()==null || Baby_account.getSpeaker_id()==0){
        return  "One of the fields is incomplete";                                      
        }
        boolean check=baby_account_Model.checkBaby_account(Baby_account);
        if(check){
            return "A baby account already exists in the system";
        }
        else {
            return baby_account_Model.create_Baby_account(Baby_account);  
        }
    }

    public String delete_Baby_account(Baby_account Baby_account) {
        boolean check=baby_account_Model.checkBaby_account(Baby_account);
        if(!check){
            return "A baby account isnot found";
        }
        else {
            return  baby_account_Model.delete_Baby_account(Baby_account);
        }

       
    }

    
    public Map getPagesNum(int pageSize) {
       
        List list = baby_account_Model.findAll();
        
        int PagesNum = ApiFunctions.pageSize(pageSize, list);
        List completedList = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, list);

        Map map = new HashMap<>();
        map.put("SumPages", PagesNum);
        map.put("data", completedList);
        return map;
    }
    
    public List getBaby_accountByPageNum(int size, int currentPage) {
        List list_Baby_account = baby_account_Model.findAll();
        List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, list_Baby_account);
        return completedList;
    }


    public Map getPagesNumOfBaby_accountsArchive(int pageSize) {
        List list = baby_account_Model.findAll();
        List filteringList = (List)list.stream().filter(s -> ((Baby_account) s).getIs_active() == true).collect(Collectors.toList());
        int PagesNum = ApiFunctions.pageSize(pageSize, filteringList);
        List completedList = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, filteringList);
        Map map = new HashMap<>();
        map.put("SumPages", PagesNum);
        map.put("data", completedList);
        return map;
    }

    public List getArchiveBaby_accountByPageNum(int size, int currentPage) {
        List list = baby_account_Model.findAll();
        List filteringList = (List)list.stream().filter(s -> ((Baby_account) s).getIs_active() == true).collect(Collectors.toList());
        List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, filteringList);
        return completedList;
    }


    public Map getPagesNumOfSearchBaby_account(int size, String search) {
        // fidls to search only with type String 
        String [] tableColumns =  new String[]{"sex", "id_num","name"};
        // search in table
        List completedList = searchEntity.matchSearchInTable(search, "Baby_account", tableColumns);
        // return object' that consist- num page, and list Hospital
        Map map = searchEntity.searchPattern(size, completedList);
        return map;
}

    public List getSearchBaby_accountByPageNum(int size, String search, int currentPage) {
    // fidls to search only with type String 
    String [] tableColumns =  new String[]{"sex", "id_num","name"};
    // search in table
    List list = searchEntity.matchSearchInTable(search, " baby_account", tableColumns);
    // return object' that consist- list Hospital 
    List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, list);
    return completedList;
}


public String discharge (Baby_account Baby_account){
    
    return baby_account_Model.discharge(Baby_account) ;   
     
}

}