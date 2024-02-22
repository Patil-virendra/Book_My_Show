package com.bookmyshow.repositories;

import com.bookmyshow.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepo extends JpaRepository<Food,String> {
}
