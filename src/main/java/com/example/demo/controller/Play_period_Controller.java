package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest.Headers;
import org.w3c.dom.DOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.services.Play_period_Service;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.functions.ResponseHandler;
import com.example.demo.models.Play_period;
import com.example.demo.services.Play_period_Service;

@RestController
@RequestMapping("PlayPeriod")
public class Play_period_Controller {

    @Autowired
    Play_period_Service Play_period_Service;

    @GetMapping("getAll")
    public ResponseEntity getAll() {
        try {
            List list = Play_period_Service.findAllDB();
            
            return new ResponseHandler(list, 200, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseHandler(e, 400, "eroor get all");

        }
    }

    @PutMapping("findByID")
    public ResponseEntity getByID(@RequestBody Map<String, Integer> index) {
        try {
            System.out.println(" find by id controller");
            Play_period ID = Play_period_Service.findByID(index);

            return new ResponseHandler(ID, 200, "error try findById");

        } catch (Exception e) {
            return new ResponseHandler(e, 400, "error get all");
        }
    }

    @PutMapping("findBaby")
    public ResponseEntity getBaby(@RequestBody Map<String, Integer> index) {
        try {
            System.out.println(" find by id controller");
            List<Play_period> getByID = Play_period_Service.findBaby(index);

            return new ResponseHandler(getByID, 200, "error find baby");

        } catch (Exception e) {
            return new ResponseHandler(e, 400, "error get all");
        }
    }

    @PutMapping("findByBabyEndDate")
    public ResponseEntity getBabyEndDate(@RequestBody Map<String, Integer> index) {
        try {

            List<Play_period> babyEndID = Play_period_Service.findbyBabyEndDate(index);

            return new ResponseHandler(babyEndID, 200, "error find baby");

        } catch (Exception e) {
            return new ResponseHandler(e, 400, "error get all");
        }
    }

    @PostMapping("create")
    public Play_period createUser(@RequestBody Map<String, Integer> jsonSearch) {
        try {
            System.out.println("create controller");
            return Play_period_Service.create(jsonSearch);

        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("upDate")
    public void upDate(@RequestBody Map<String, Integer> information) {
        try {
            System.out.println("up date controller");
            Play_period_Service.upDate(information);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @PutMapping("findHospital")
    public List<Play_period> findHospital(@RequestBody Map<String, Integer> hospital) {
        try {
            List<Play_period> hospitalList = Play_period_Service.findhospital1(hospital);
            return hospitalList;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("playTimesOfBaby")
    public ResponseEntity playTimesOfBaby(@RequestBody Map<String, Integer> times) {
        try {
            System.out.println("playTimesOfBaby controller");

            List<Play_period> list = Play_period_Service.playTimes(times);
            
//    --playbackType A = Playback times every day according to a unique definition                  //
//    --playbackType B = Playback times are determined in a deductive manner in a personal way     //
//    --*WARNING*  in the event that playback times were not defined on that day, the hospital's depulative mode will be activated //
//    --playbackType C = Playback times are determined by the hospital (hospital depulative mode)  //

            if (list.get(0).getBaby_acount_id() != null) {
                if (list.get(0).getDate() == null) {
                
                     list.get(0).setPlaybackType("A");
                    
                    return new ResponseHandler(list, 200, "Situation", "Permanent personal status ", null);
                } else {
                    list.get(0).setPlaybackType("B");
                    return new ResponseHandler(list, 200, "Situation", "Personal situation changes", null);
                }
            } else {
                list.get(0).setPlaybackType("C");
                return new ResponseHandler(list, 200, "Situation", "Permanent hospital status", null);
            }
        } catch (Exception e) {
            return new ResponseHandler(e, 400, "error playTimesOfBaby");
        }
    }

    @DeleteMapping("deleteByID")
    public void deletebyid(@RequestBody Map<String, Integer> id) {
        try {
            Play_period_Service.delete(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @DeleteMapping("deleteByBabyAcount")
    public void deleteByBabyAcount(@RequestBody Map<String, Integer> babyAcount) {
        try {
            System.out.println("deleteByBabyAcount controller");
            Play_period_Service.deleteBabyAcont(babyAcount);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
