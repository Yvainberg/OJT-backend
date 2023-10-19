package com.example.demo.controller;

import com.example.demo.functions.MailService;
import com.example.demo.functions.ResponseHandler;
import com.example.demo.models.Users;
import com.example.demo.services.Users_Service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
     
  @Autowired
  Users_Service users_Service;


  @PostMapping("forgot_password")
  public ResponseEntity message(@RequestBody Map<String, String> _email) {
    try {
      String email = (String)_email.get("email");
      users_Service.message_email_forgot_pass(email);
      return new ResponseHandler("Message will send to your email", 200, null);     
    } catch (Exception e) {
      return new ResponseHandler(e.getMessage(), 400, null);
    }
  }


  @PostMapping("/signIn")
  public ResponseEntity signIn(@RequestBody Map<String, Object> jsonData) {
    try {
      String email = (String) jsonData.get("email_adress");
      String password = (String) jsonData.get("password");
      Map userValidation = users_Service.signIn(email, password);
      return new ResponseHandler(userValidation, 200, null);
    } catch (Exception e) {
      return new ResponseHandler(e, 400, null);
      // TODO: handle exception
    }
  }


  @PostMapping("setOrChangePassword")
  public ResponseEntity setOrChangePassword(@RequestBody Map<String, Object> jsonData) {
      try {
        users_Service.setOrChangePassword(jsonData);
      return new ResponseHandler("", 200, null);     
    } catch (Exception e) {
      return new ResponseHandler(e.getMessage(), 400, null);
    }
  }

}
