package com.bookmyshow.services;

import com.bookmyshow.payloads.PaymentTicketDto;

import java.util.List;

public interface PaymentTicketService {



  //  PaymentTicketDto makePayment(PaymentTicketDto paymentDto);

    public PaymentTicketDto createTicket(PaymentTicketDto paymentTicketDto);

    public List<PaymentTicketDto >  getAllPaymentTickets();

   //   public List<PaymentTicketDto>  getTicketByUserEmail(String userEmail);

}
