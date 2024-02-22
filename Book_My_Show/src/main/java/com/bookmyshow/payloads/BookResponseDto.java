package com.bookmyshow.payloads;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookResponseDto {

    private Integer bookingId;

    private  String fname;

    private String  email;

    private String movieName;

    private String moviePoster;

    private  String theaterName;

    private  String theaterLocation;

    private List<String> seats;

    private  String showtime;

    private LocalDate showDate;

    private String foodName;

    private  Double fooodPrice;

    private  double ticketPrice;

    private double totalPrice;


}
