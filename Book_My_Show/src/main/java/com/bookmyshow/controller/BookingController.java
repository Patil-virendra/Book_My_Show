package com.bookmyshow.controller;

import com.bookmyshow.payloads.BookingDto;
import com.bookmyshow.services.BookingService;
import com.bookmyshow.services.impl.BookingServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

     @Autowired
     private BookingService bookingService;

     @Autowired
     private BookingServiceImpl bookingServiceImpl;

    @Autowired
     private ModelMapper modelMapper;

         @PostMapping("/pay-now")
       public ResponseEntity<BookingDto> createBooking(/*@Valid*/ @RequestBody BookingDto bookingDto)
       {

             BookingDto bookingDto1= this.bookingService.createBooking(bookingDto);


         //  BookResponseDto bookResponseDto= this.modelMapper.map(bookingDto1,BookResponseDto.class);
         //  BookResponseDto  bookResponseDto= bookingServiceImpl.bookingDtoToBookingResponseDto(bookingDto1);

           return new ResponseEntity<BookingDto>(bookingDto1, HttpStatus.CREATED);
       }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/view-bookings")
    public ResponseEntity<List<BookingDto>> getAllTickets(){
        List<BookingDto> allBookedTickets = bookingService.getAllBookedTickets();
        return new ResponseEntity<List<BookingDto>>(allBookedTickets,HttpStatus.OK);
    }

    @GetMapping("/booked-tickets-details/{userEmail}")
    public ResponseEntity<List<BookingDto>> getTicketsByUserEmail(@PathVariable("userEmail") String userEmail){
        List<BookingDto> ticketsByUserEmail = bookingService.getTicketsByUserEmail(userEmail);
        return new ResponseEntity<List<BookingDto>>(ticketsByUserEmail,HttpStatus.OK);
    }




}




