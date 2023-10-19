package com.example.demo.functions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service
public class SearchEntity {

    @PersistenceContext
    EntityManager entityManager;
    
    public List matchSearchInTable (String search, String nameClass, String[] fildsClass) {
        String str = "FROM " + nameClass + " WHERE ";
        
        String[] map = fildsClass;
        
        int length = map.length;
        int index = 1;

        for (String key : map) {
            str += key + " like " + "'" + search + "%'";
            if(index < length){
                str += " OR ";
            }
            index++;
        }
        // System.out.println("\n\n Query string = " + str + "\n\n");

        Query query = entityManager.createQuery(str);
        List list = new ArrayList<>(){};

        if(query.getHints().size() == 0){
            return list;
        }
        return query.getResultList();
    }


    public Map searchPattern(int size, List searchResult) {
        Map map = new HashMap<>();
        
        List list = searchResult;

        if(list == null || list.size() == 0){
            System.out.println("\n size searchResult = " + list.size());
            map.put("data" , list);
            map.put("SumPages" , 0);
            return map;
        }

        int pagesSum = ApiFunctions.pageSize(size, list);
        List firstGroup = ApiFunctions.getNumObjectsByNumPage(size, 1, list);
        
        map.put("data" , firstGroup);
        map.put("SumPages" , pagesSum);
        
        return map;
    }

    
}
