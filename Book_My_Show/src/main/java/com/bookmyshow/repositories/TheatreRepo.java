package com.bookmyshow.repositories;

import com.bookmyshow.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatreRepo extends JpaRepository<Theatre,String> {

   Optional<Theatre> findByTheaterName(String theatreName);
}
