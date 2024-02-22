package com.bookmyshow.repositories;

import com.bookmyshow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User,String> {

      List<User>findByEmailAndFname(String email, String fname);

      Optional<User> findByEmail(String email);

}
