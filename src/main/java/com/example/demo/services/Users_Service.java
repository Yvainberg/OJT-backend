package com.example.demo.services;

import com.example.demo.functions.ApiFunctions;
import com.example.demo.functions.CodeGenerator;
import com.example.demo.functions.MailService;
import com.example.demo.functions.ResponseHandler;
import com.example.demo.functions.SearchEntity;
import com.example.demo.models.Users;
import com.example.demo.models.Users_Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.AEADBadTagException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.functions.ApiFunctions;
import com.example.demo.functions.ResponseHandler;
import com.example.demo.functions.SearchEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Users_Service {

  @Autowired
  Users_Model users_Model;
 
  @Autowired
  SearchEntity searchEntity;

  // A function that sends an email
  @Autowired
  MailService mailService;

  // A function that generates a 4-digit temporary random code
  @Autowired
  CodeGenerator codeGenerator;

  public Map getPagesNum(int pageSize) {
    List list = users_Model.findAll();

    int pagesSum = ApiFunctions.pageSize(pageSize, list);
    List completedList = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, list);

    Map map = new HashMap<>();
    map.put("SumPages", pagesSum);
    map.put("data", completedList);
    return map;
  }

  
    public List getByPageNum(int size, int currentPage) {
        List list = users_Model.findAll();

        List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, list);
        return completedList;

    }

    public Map getPagesNumOfArchive(int pageSize) {
        List list = users_Model.findAll();

        List filteringList = (List) list.stream().filter(s -> ((Users) s).isIs_active() == true)
                .collect(Collectors.toList());
        int pagesSum = ApiFunctions.pageSize(pageSize, filteringList);

        List completedList = ApiFunctions.getNumObjectsByNumPage(pageSize, 1, filteringList);

        Map map = new HashMap<>();
        map.put("SumPages", pagesSum);
        map.put("data", completedList);
        return map;
    }

    public List getArchiveByPageNum(int size, int currentPage) {
        List list = users_Model.findAll();

        List filteringList = (List) list.stream().filter(s -> ((Users) s).isIs_active() == true)
                .collect(Collectors.toList());
        List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, filteringList);
        return completedList;
    }

    public Map getPagesNumOfSearch(int size, String search) {
        // fidls to search only with type String
        String[] tableColumns = new String[] { "first_name", "last_name", "email_adress", "phone_num", "city", "street",
                "house", "apartment", "id_num" };
        // search in table
        List completedList = searchEntity.matchSearchInTable(search, "Users", tableColumns);
        // return object' that consist, num page, and
        Map map = searchEntity.searchPattern(size, completedList);
        return map;
    }

    public List getSearchByPageNum(int size, String search, int currentPage) {
        System.out.println("currentPage = " + currentPage);
        // fidls to search only with type String
        String[] tableColumns = new String[] { "first_name", "last_name", "email_adress", "phone_num", "city", "street",
                "house", "apartment", "id_num" };
        // search in table
        List list = searchEntity.matchSearchInTable(search, "Users", tableColumns);
        // return object' that consist, num page, and
        List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, list);
        return completedList;
    }

    public Map getPagesNumOfArchiveAndSearch(int size, String search) {
        // fidls to search only with type String
        String[] tableColumns = new String[] { "first_name", "last_name", "email_adress", "phone_num", "city", "street",
                "house", "apartment", "id_num" };
        // search in table
        List completedList = searchEntity.matchSearchInTable(search, "Users", tableColumns);
        // return object' that consist, num page, and
        List filteringList = (List) completedList.stream().filter(s -> ((Users) s).isIs_active() == true)
                .collect(Collectors.toList());

        Map map = searchEntity.searchPattern(size, filteringList);
        return map;
    }

    public List getArchiveAndSearchByPageNum(int size, String search, int currentPage) {
        System.out.println("currentPage = " + currentPage);
        // fidls to search only with type String
        String[] tableColumns = new String[] { "first_name", "last_name", "email_adress", "phone_num", "city", "street",
                "house", "apartment", "id_num" };
        // search in table
        List list = searchEntity.matchSearchInTable(search, "Users", tableColumns);

        List filteringList = (List) list.stream().filter(s -> ((Users) s).isIs_active() == true)
                .collect(Collectors.toList());

        // return object' that consist, num page, and
        List completedList = ApiFunctions.getNumObjectsByNumPage(size, currentPage, filteringList);
        return completedList;
    }

    public String create(Map<String, Object> map) {

      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      Users createNew = objectMapper.convertValue(map, Users.class);

      // Check if email exist
      Users isUsersExist = users_Model.findByEmail_address((String)createNew.getEmail_adress());
      if(isUsersExist != null){
        throw new ArithmeticException("this user already exists");
      }
        
      // Temporary random code 4 digits long
      String tempCode = codeGenerator.generateCode();
      // Set tempCode in entity user
      createNew.setTempPassword(tempCode);
      // Set tempCode in DB
      users_Model.createOrUpdate(createNew);
      // TO DO: Link to the 'Create Password' page and embed in the email message
      String email = createNew.getEmail_adress();
      mailService.sendEmail(email, "Create password" ,"Enter this code in the link " + tempCode + "<a href= 'http://localhost:3000/changePassword?email="+email+"'> To create password </a>" );
      
      System.out.println("getId()" + createNew.getId());
      return "Message will send to your email";
  }

    public Users update(Map<String, Object> map) {
        int id = (int) map.get("id");

        if (users_Model.findById(id) == null) {
            throw new ArithmeticException("this id is not exist");
        }
        Users user = null;
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            user = objectMapper.convertValue(map, Users.class);
        } catch (Exception e) {
            throw new ArithmeticException("one fild invalid");
        }

        return users_Model.createOrUpdate(user);
    }

    public Users moveToArchieved(Map<String, Object> map) {
        int id = (int) map.get("id");
        boolean is_active = (boolean) map.get("is_active");

        if (users_Model.findById(id) == null) {
            throw new ArithmeticException("this id is not exist");
        }

        if (is_active == true) {
            throw new ArithmeticException("There is no change from the existing status");
        } else {

            Users exsistingUser = users_Model.findById(id);
            exsistingUser.setIs_active(false);
            
            return users_Model.createOrUpdate(exsistingUser);
        }

    }

    //***need to complete***/
    public String message_email_forgot_pass(String email) {
      // Checking that the email exists in a database
      Users user = users_Model.findByEmail_address(email);
      if (user == null) {
      // Message: user does not exist     
         throw new ArithmeticException  ("You are not allowed to create a new password");
      }
      // Temporary random code 4 digits long
      String tempCode = codeGenerator.generateCode();
      // Set tempCode in entity user
      user.setTempPassword(tempCode);
      // Set tempCode in DB
      users_Model.createOrUpdate(user);
      // TODO:
      // * Link to the 'Create Password' page and embed in the email message.
      mailService.sendEmail(email, "Create password","enter this code in the link " + tempCode + "<a href= 'http://localhost:3000/changePassword?email="+email+"'> To create password </a>" );
  
      return "Message will send to your email";
    } 


  // sign in check
  public Map signIn(String email, String password) {
    Users user = users_Model.findByEmail_address(email);
    if (user != null) { // validation
      //TODO: send to a function that hides the password
       // and make a TOKENNNNNNN
      if (user.getPassword().equals(password)) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("first_name", user.getFirst_name());
        map.put("email_adress", user.getEmail_adress());
        map.put("permission", user.getPermission());
        return map;
      }
      throw new ArithmeticException("the password isn't correct");// password not currect
    }
    throw new ArithmeticException("These email don't exist");// did not find a email
  }

  
  public void setOrChangePassword(Map jsonData){

    String email = (String)jsonData.get("email");
    String tempPass = (String)jsonData.get("tempPass");
    String pass1 = (String)jsonData.get("pass1");
    String pass2 = (String)jsonData.get("pass2");

    if(pass1 != pass2)
         throw new ArithmeticException("Pass not math");
      
      Users user = users_Model.findByEmail_address(email);

      if(user == null)
        throw new ArithmeticException("Email not exist");
      
      if(user.getTempPassword().equals(tempPass) == false)
        throw new ArithmeticException("Pass not good");

      user.setTempPassword(null);
      user.setPassword(pass1);
      users_Model.createOrUpdate(user);
  }

}
