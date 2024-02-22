package com.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private String paymentId;

     private double totalPrice;

     private String status;


     @OneToOne
     private  Booking booking;

     @ManyToOne
     private  AtmCard atmCard;

     @ManyToOne
     private User user;



}
