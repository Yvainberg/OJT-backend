package com.example.demo.models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;



interface Repository_Baby_account extends JpaRepository<Baby_account, Integer>{
    boolean existsByid (int i);
}


@Service
public class Baby_account_Model {

    @Autowired
 
    Repository_Baby_account repository_Baby_account;

    public boolean checkBaby_account(Baby_account Baby_account){
       return repository_Baby_account.existsByid(Baby_account.getId());
    }

    public  String create_Baby_account(Baby_account Baby_account){
        repository_Baby_account.save(Baby_account);
        return "Baby_account create successfully";
       
    }

    public String delete_Baby_account(Baby_account Baby_account) {
        Baby_account.setIs_active(false);
        repository_Baby_account.save(Baby_account);
        return  "Baby_account delete successfully";
    }
   
    public List<Baby_account> findAll(){
        return repository_Baby_account.findAll();
    }
    
    public Baby_account findById(int index){
        try{
            return repository_Baby_account.findById(index).get();
        }
        catch(Exception e){
            return null;
        }
    }

    public String discharge( Baby_account Baby_account){
       Baby_account.setIs_active(false);
        return "Baby_account update successfully";
    }

}
