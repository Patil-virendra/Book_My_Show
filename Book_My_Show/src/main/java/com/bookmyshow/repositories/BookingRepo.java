package com.bookmyshow.repositories;


import com.bookmyshow.entity.Booking;
import com.bookmyshow.payloads.BookingDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Integer> {

  // List<Booking> findByEmail(String userEmail);

   List<Booking> findByUserEmail(String userEmail);
}
