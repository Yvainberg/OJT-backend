package com.example.demo.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Repository
interface Repository_Users extends JpaRepository<Users, Integer> {
  @Query("SELECT u FROM Users u WHERE u.email_adress = ?1")
  Optional<Users> findByemail_adress(String email_adress);
}


@Service
public class Users_Model {

  @Autowired
  Repository_Users repository_Users;

  @PersistenceContext
  private EntityManager entityManager;

  public List<Users> findAll() {
    return repository_Users.findAll();
  }

  public Users findById(int index) {
    try {
      return repository_Users.findById(index).get();
    
    } catch (Exception e) {
      return null;
    }
  }

 
  public Users findByEmail_address(String email) {
    try {
      System.out.println("try Model findOneByEmail_adress");
      Optional optional = repository_Users.findByemail_adress(email); 
      return (Users) optional.get();
      
    } catch (Exception e) {
      System.out.println("catch catch catch");
        return (Users)null;
      }
      
  }
  
    
    public Users createOrUpdate(Users user) {
        try {
            return repository_Users.save(user);
        } catch (Exception e) {
            return null;
        }
    }
   
}
