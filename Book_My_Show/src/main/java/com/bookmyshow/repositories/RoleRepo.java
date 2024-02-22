package com.bookmyshow.repositories;


import com.bookmyshow.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}

