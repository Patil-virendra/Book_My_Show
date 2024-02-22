package com.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer bookingId;

     @ManyToOne
     private User user;

     @ManyToOne
     private Movie movie;

     @ManyToOne
     private Theatre theatre;

     @ManyToOne
     private  Shows shows;

//     private  String showtime;

     private LocalDate showDate;

     private List<String> bokedSeats;

     @ManyToOne
     private Food food;

     private BigDecimal bookedSeatsPrice;

     private BigDecimal totalPrice;

     @OneToOne(mappedBy = "booking",cascade = CascadeType.ALL)
     private  Payment payment;


}
