package com.bookmyshow.services;

import com.bookmyshow.payloads.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto createBooking(BookingDto bookingDto);

   List<BookingDto> getAllBookedTickets();


    List<BookingDto>  getTicketsByUserEmail( String userEmail);
}
