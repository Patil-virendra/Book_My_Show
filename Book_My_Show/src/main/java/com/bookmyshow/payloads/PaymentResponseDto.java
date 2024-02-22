package com.bookmyshow.payloads;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PaymentResponseDto {

    private String paymentId;

    private  String fname;

    private String  email;

    private String movieName;
  //  private String moviePoster;

    private  String theaterName;

    private  String theaterLocation;

    private List<String> seats;

    private  String showtime;

    private LocalDate showDate;

    private String foodName;

    private  Double fooodPrice;

    private  double ticketPrice;

    private double totalPrice;

    private String cardNo;

    private  String cardHolderName;

    private String expiryDate;

    private  String paymentStatus;

    private String message;


}
