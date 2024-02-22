package com.bookmyshow.services.impl;

import com.bookmyshow.entity.Booking;
import com.bookmyshow.entity.Payment;
import com.bookmyshow.entity.User;
import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.payloads.PaymentTicketDto;
import com.bookmyshow.repositories.AtmCardRepo;
import com.bookmyshow.repositories.BookingRepo;
import com.bookmyshow.repositories.PaymentTicketRepo;
import com.bookmyshow.repositories.UserRepo;
import com.bookmyshow.services.PaymentTicketService;
import com.bookmyshow.utility.UtilityMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentTicketServiceImpl implements PaymentTicketService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PaymentTicketRepo paymentRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AtmCardRepo atmCardRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private  PaymentTicketRepo paymentTicketRepo;

    @Override
    public PaymentTicketDto createTicket(PaymentTicketDto paymentTicketDto) {

        Payment payment = new Payment();
        Booking booking = bookingRepo.findById(Integer.valueOf(paymentTicketDto.getBookingId())).orElseThrow(() -> new ResourceNotFoundException("Booking", "booking id", String.valueOf(paymentTicketDto.getBookingId())));
        //   payment=booking.getPayment();
        //  payment.setStatus("paid");


//        if(payment != null){
//            payment.setAtmCard(utilityMethods.createCard(paymentTicketDto));
//            paymentRepo.save(payment);
//        }else {
        //   payment = utilityMethods.createPayment(paymentTicketDto);
        if (!booking.getBokedSeats().equals(paymentTicketDto.getBookedSeats())) {
            throw new ResourceNotFoundException("Seats don't match");
        }


        payment.setAtmCard(utilityMethods.createCard(paymentTicketDto));
        payment.setTotalPrice(booking.getTotalPrice().doubleValue());
         booking.getUser().getId();
       // Optional<User> user1=userRepo.findByEmail(paymentTicketDto.getEmail());
        User user= userRepo.findByEmail(booking.getUser().getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserEmail", booking.getUser().getEmail()));
        payment.setBooking(booking);
        payment.setStatus("paid");
        payment.setUser(user);
        paymentRepo.save(payment);
        booking.setPayment(payment);
        bookingRepo.save(booking);


        return bookingToPaymentTicketDto(booking);

    }

    @Override
    public List<PaymentTicketDto> getAllPaymentTickets() {

       List<Payment> listOfTicket = paymentTicketRepo.findAll();
        if (listOfTicket.isEmpty()){
            return new ArrayList<PaymentTicketDto>();
        }
        else {
            List<PaymentTicketDto> paymentTicketDtoList = listOfTicket.stream()
                    .map(b1 -> bookingToPaymentTicketDto(b1.getBooking()))
                    .collect(Collectors.toList());
            return paymentTicketDtoList;
        }

    }




    private PaymentTicketDto bookingToPaymentTicketDto(Booking booking)
    {
        PaymentTicketDto paymentTicketDto=new PaymentTicketDto();

            paymentTicketDto.setPaymentId(booking.getPayment().getPaymentId());
            paymentTicketDto.setBookingId(booking.getBookingId());
            paymentTicketDto.setFname(booking.getUser().getFname());
            paymentTicketDto.setEmail(booking.getUser().getEmail());
            paymentTicketDto.setMovieName(booking.getMovie().getMovieName());
      //      paymentTicketDto.setMoviePoster(booking.getMovie().getMoviePoster());
            paymentTicketDto.setTheaterName(booking.getTheatre().getTheaterName());
            paymentTicketDto.setTheaterLocation(booking.getTheatre().getTheaterLocation());
            paymentTicketDto.setShowtime(String.valueOf(booking.getShows().getShowTime()));
            paymentTicketDto.setShowDate(booking.getShowDate());
            paymentTicketDto.setBookedSeats(booking.getBokedSeats());
            paymentTicketDto.setFood(booking.getFood().getFoodName());
            paymentTicketDto.setFoodPrice(Double.valueOf(booking.getFood().getFoodPrice()));
            paymentTicketDto.setBookedSeatsPrice(booking.getBookedSeatsPrice());
            paymentTicketDto.setTotalPrice(booking.getTotalPrice());
         //   paymentTicketDto.setTotalPrice(booking.getPayment().getBooking().getTotalPrice());
            paymentTicketDto.setCardNo(booking.getPayment().getAtmCard().getCardNo());
            paymentTicketDto.setCardHolderName(booking.getPayment().getAtmCard().getCardHolderName());
            paymentTicketDto.setExpiryDate(booking.getPayment().getAtmCard().getExpiryDate());
            paymentTicketDto.setPaymentStatus("Payment Is Done Succssfully");
            paymentTicketDto.setMessage("Congratulation Your Ticket is Booked.");
        return paymentTicketDto;
    }





    //   @Override
//    public PaymentTicketDto makePayment(PaymentTicketDto paymentDto) {
//
//        Integer bookingId= paymentDto.getBooking().getBookingId();
//
//         Booking booking = this.bookingRepo.findById(bookingId)
//                      .orElseThrow(()->new ResourceNotFoundException("Booking","Id",String.valueOf(bookingId)));
//
//
//           AtmCardDto atmCardDto =paymentDto.getAtmCard();
//           AtmCard atmCard=this.modelMapper.map(atmCardDto, AtmCard.class);
//           AtmCard savedAtmCard=this.atmCardRepo.save(atmCard);
//
//
//           Payment payment=this.modelMapper.map(paymentDto, Payment.class);
//           payment.setBooking(booking);
//           payment.setAtmCard(savedAtmCard);
//           Payment payment1= this.paymentRepo.save(payment);
//
//        return this.modelMapper.map(payment1, PaymentTicketDto.class);
//    }

//    public PaymentResponseDto paymentDtoToPaymentResponseDto(PaymentTicketDto paymentDto1)
//    {
//        PaymentResponseDto payResDto=new PaymentResponseDto();
//
//        payResDto.setPaymentId(paymentDto1.getPaymentId());
//        payResDto.setFname(paymentDto1.getBooking().getUser().getFname());
//        payResDto.setEmail(paymentDto1.getBooking().getUser().getEmail());
//        payResDto.setMovieName(paymentDto1.getBooking().getMovie().getMovieName());
//        payResDto.setTheaterName(paymentDto1.getBooking().getTheatre().getTheaterName());
//        payResDto.setTheaterLocation(paymentDto1.getBooking().getTheatre().getTheaterLocation());
//        payResDto.setSeats(paymentDto1.getBooking().getSeats());
//        payResDto.setShowDate(paymentDto1.getBooking().getShowDate());
//        payResDto.setShowtime(paymentDto1.getBooking().getShowtime());
//        payResDto.setFoodName(paymentDto1.getBooking().getFood().getFoodName());
//        payResDto.setFooodPrice(paymentDto1.getBooking().getFood().getFooodPrice());
//        payResDto.setTicketPrice(paymentDto1.getBooking().getTicketPrice());
//        payResDto.setTotalPrice(paymentDto1.getBooking().getTotalPrice());
//        payResDto.setCardNo(paymentDto1.getAtmCard().getCardNo());
//        payResDto.setCardHolderName(paymentDto1.getAtmCard().getCardHolderName());
//        payResDto.setExpiryDate(paymentDto1.getAtmCard().getExpiryDate());
//        payResDto.setPaymentStatus("Your Payment Is Done Successfully(Paid)");
//        payResDto.setMessage("congratulations, Your Ticket Is Booked.");
//
//        return payResDto;
//    }
//
//    @Override
//    public List<PaymentTicketDto>  getTicketByUserEmail(String userEmail) {
//
//          List<Payment>  paymentList= paymentTicketRepo.findByEmail(userEmail);
//        if (paymentList.isEmpty()){
//            throw new ResourceNotFoundException("Ticket","user email",userEmail);
//        }
//        else {
//            return paymentList.stream()
//                    .map(b2 -> bookingToPaymentTicketDto(b2.getBooking()))
//                    .collect(Collectors.toList());
//        }
//        return null;
//    }

}
