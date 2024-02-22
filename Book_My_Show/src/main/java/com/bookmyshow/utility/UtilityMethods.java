package com.bookmyshow.utility;

import com.bookmyshow.entity.*;
import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.payloads.BookingDto;
import com.bookmyshow.payloads.PaymentTicketDto;
import com.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Component
public class UtilityMethods {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private ShowsRepo showsRepo;

    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private PaymentTicketRepo paymentRepo;

    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private AtmCardRepo atmCardRepo;



//    @Transactional
//    public Payment createPayment(BookingDto bookingDto) {
//        User user = userRepo.findByEmail(bookingDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "User ID", bookingDto.getEmail()));
//
//        Payment payment = new Payment();
//
//        payment.setTotalPrice(Double.valueOf(calculateTotalPrice(bookingDto)));
//        payment.setUser(user);
////        payment.setCard(createCard(bookingDto));
//
//        paymentRepo.save(payment);
////        payment.setBooking();
//        return payment;
  //  }
//    @Transactional
//    public  Payment createPayment(PaymentTicketDto paymentTicketDto) {
//        User user = userRepo.findByEmail(paymentTicketDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "User ID", paymentTicketDto.getEmail()));
//
//        Payment payment = new Payment();
////        payment.setId(String.valueOf(user.getId())); //we have set the same user Id for payment ID
//        payment.setTotalPrice(paymentTicketDto.getTotalPrice().doubleValue());
//        payment.setUser(user);
//        payment.setAtmCard(createCard(paymentTicketDto));
//
//        paymentRepo.save(payment);
////        payment.setBooking();
//        return payment;
//    }
    @Transactional
    public AtmCard createCard(PaymentTicketDto paymentTicketDto){
        User user = userRepo.findByEmail(paymentTicketDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User","User ID",paymentTicketDto.getEmail()));

        Optional<AtmCard> card = atmCardRepo.findById(paymentTicketDto.getCardNo());

        if(card.isPresent()){
            return card.get();
        }
        else {
            AtmCard newCard = new AtmCard();
            newCard.setCardNo(paymentTicketDto.getCardNo());
            newCard.setCardHolderName(paymentTicketDto.getCardHolderName());
//            newCard.setCvv(ticketDto.getCvv());
            newCard.setExpiryDate(paymentTicketDto.getExpiryDate());
            newCard.setUser(user);
//            newCard.setPayments();

            atmCardRepo.save(newCard);

            return newCard;
        }
    }
    @Transactional
    public Shows createShows(BookingDto bookingDto){

        Movie movie = movieRepo.findByMovieName(bookingDto.getMovieName())
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "withMovieName", bookingDto.getMovieName()));


        Theatre theatre = theatreRepo.findByTheaterName(bookingDto.getTheaterName())
                .orElseThrow(() -> new ResourceNotFoundException("Theatre", "Id", bookingDto.getTheaterName()));

         LocalTime localTime= LocalTime.parse(bookingDto.getShowtime());
         Optional<Shows>shows11 =showsRepo.findByShowTime(localTime);


         if(shows11.isPresent()){
             return shows11.get();
         }
         else {
            Shows showsObj=new Shows();
             LocalTime localTime2= LocalTime.parse(bookingDto.getShowtime());
              showsObj.setShowTime(localTime2);
              showsObj.setMovie(movie);
              showsObj.setTheatre(theatre);

              showsRepo.save(showsObj);

             return showsObj;
         }
    }
    public Integer calculateFoodPrice(BookingDto bookingDto) {

        String[] array = bookingDto.getFood().split("\\s");
        String price = array[array.length - 1];
        int foodPrice = Integer.parseInt(price);

        Food foodItem = foodRepo.findById(bookingDto.getFood()).orElseThrow(() -> new ResourceNotFoundException("food", "food name", bookingDto.getFood()));

        if (foodItem.getFoodPrice() == foodPrice) {
            return foodPrice;
        } else {

            throw new ResourceNotFoundException("food price do not match");
        }
    }

    public Integer calculateSeatPrice(BookingDto bookingDto){
        Integer priceOfBookedSeat = bookingDto.getBookedSeats().stream().map(seat -> AppConstants.SILVER.contains(seat) ? AppConstants.SILVER_SEAT_PRICE :
                AppConstants.GOLD.contains(seat) ? AppConstants.GOLD_SEAT_PRICE : AppConstants.PLATINUM.contains(seat) ? AppConstants.PLATINUM_SEAT_PRICE :
                        AppConstants.DIAMOND.contains(seat) ? AppConstants.DIAMOND_SEAT_PRICE : 0).reduce(Integer::sum).get();

        return priceOfBookedSeat;
    }


    public Integer calculateTotalPrice(BookingDto bookingDto){
        return (calculateSeatPrice(bookingDto)+calculateFoodPrice(bookingDto));
    }


    @Transactional
    public Food saveFoodItem(BookingDto bookingDto) {
        Food foodItem = foodRepo.findById(bookingDto.getFood()).orElseThrow(() -> new ResourceNotFoundException("Food", "Food name", bookingDto.getFood()));
        return foodItem;


    }

}
