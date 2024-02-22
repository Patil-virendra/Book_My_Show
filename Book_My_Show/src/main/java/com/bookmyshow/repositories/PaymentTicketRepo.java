package com.bookmyshow.repositories;

import com.bookmyshow.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTicketRepo extends JpaRepository<Payment,String> {

      //  Payment findByEmail(String userEmail);


}
