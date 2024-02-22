package com.bookmyshow.repositories;

import com.bookmyshow.entity.Shows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface ShowsRepo extends JpaRepository<Shows,Integer> {

    Optional<Shows> findByShowTime(LocalTime showTime);
}
