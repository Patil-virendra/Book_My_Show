package com.bookmyshow.payloads;



import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto  {


    private Integer bookingId;

//    private UserDto user;
//
//    private MovieDto movie;
//
//    private TheatreDto theatre;
//
//    private FoodDto food;

    @NotBlank
    private  String fname;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email address")
    private String  email;

    @NotBlank
    private String movieName;

    @NotBlank
    private String moviePoster;

    @NotBlank
    private  String theaterName;

    @NotBlank
    private  String theaterLocation;

    @NotBlank
    private  String showtime;

    @NotBlank
    private LocalDate showDate;

   @NotBlank
    @Size(min = 1,max = 4,message = "At least one seat need to be selected")
    private List<String> bookedSeats;

    @NotBlank
    private String food;

    @NotBlank
    private Double foodPrice;

    @Positive
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal bookedSeatsPrice;

    @Positive
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal totalPrice;




}
