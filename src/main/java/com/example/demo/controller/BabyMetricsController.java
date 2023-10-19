
package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.functions.ResponseHandler;
import com.example.demo.models.Baby_metrics;
import com.example.demo.models.Speaker;
import com.example.demo.services.*;



@RestController
@RequestMapping("baby_metrics")
public class BabyMetricsController {
    
    @Autowired
    private BabyMetrics_servise BabyMetrics_servise;

  
   
    

    @PostMapping("/")
    public String createMetrics(@RequestBody Baby_metrics babyMetrics) {  
        try {
           return BabyMetrics_servise.createMetrics(babyMetrics);
        } 
        catch (Exception e) {
            return "error lll"; 
        }
        
    }

//     @GetMapping("getPagesNum/{pageSize}")
// public ResponseEntity getPagesNum(@PathVariable int pageSize) {
   
//     try {
//         Map map = (Map) BabyMetrics_servise.getPagesNum(pageSize);
       
//         return new ResponseHandler(map, 200, null);
//     } catch (Exception e) {
       
//         return new ResponseHandler(e, 400, null);
//     }
// }

// @GetMapping("getBaby_metricsByPageNum/{currentPage}/{pageSize}")
// public ResponseEntity getBaby_accountByPageNum(@PathVariable int currentPage, @PathVariable int pageSize) {
   
//     try {
//         List list = BabyMetrics_servise.getBaby_accountByPageNum(pageSize, currentPage);
//        return new ResponseHandler(list, 200, null);
//     } catch (Exception e) {
      
//         return new ResponseHandler(e, 400, null);
//     }
// }
@GetMapping("getAll/{currentPage}/{pageSize}")
public ResponseEntity getAll(@PathVariable int currentPage, @PathVariable int pageSize) {
    return BabyMetrics_servise.getSpeakerByNamPage(pageSize, currentPage);
}

@GetMapping("getSumPage/{pageSize}")
public ResponseEntity getSumPage(@PathVariable int pageSize) {
    return BabyMetrics_servise.getPagesSum(pageSize);
}


// 
    
        
        
       
    }





