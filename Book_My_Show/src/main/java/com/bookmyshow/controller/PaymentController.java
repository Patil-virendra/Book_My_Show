package com.bookmyshow.controller;

import com.bookmyshow.payloads.PaymentTicketDto;
import com.bookmyshow.services.PaymentTicketService;
import com.bookmyshow.services.impl.PaymentTicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@EnableWebSecurity
@Validated
public class PaymentController {

    @Autowired
    private PaymentTicketService paymentService;

    @Autowired
    private PaymentTicketServiceImpl paymentServiceImpl;

    @PostMapping("/ticket")
    public ResponseEntity<PaymentTicketDto>makeTicketPayment(@RequestBody PaymentTicketDto paymentTicketDto){

           PaymentTicketDto paymentTicketDto1=this.paymentService.createTicket(paymentTicketDto);

      //  PaymentResponseDto paymentResponseDto= paymentServiceImpl.paymentDtoToPaymentResponseDto(paymentDto1);

           return  new ResponseEntity<PaymentTicketDto>(paymentTicketDto1, HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/view-tickets")
    public ResponseEntity<List<PaymentTicketDto>> getAllTickets(){
        List<PaymentTicketDto> allTickets = paymentService.getAllPaymentTickets();
        return new ResponseEntity<List<PaymentTicketDto>>(allTickets,HttpStatus.OK);
    }



}
