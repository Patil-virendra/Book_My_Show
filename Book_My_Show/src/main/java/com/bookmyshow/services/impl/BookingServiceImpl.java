package com.bookmyshow.services.impl;

import com.bookmyshow.entity.*;
import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.payloads.*;
import com.bookmyshow.repositories.*;
//import com.bookmyshow.repositories.SeatRepo;
import com.bookmyshow.services.BookingService;
import com.bookmyshow.utility.UtilityMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserServiceImpl userServiceimpl;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private FoodRepo foodRepo;

//    @Autowired
//    private SeatRepo seatRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UtilityMethods utilityMethods;

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {

        Booking booking = new Booking();


//        String email = bookingDto.getUser().getEmail();
//        String movieName = bookingDto.getMovie().getMovieName();
//  //      Integer theatreId = bookingDto.getTheatre().getId();
//           String theaterName  =bookingDto.getTheatre().getTheaterName();
//        double ticketPrice = bookingDto.getTicketPrice();
//        double fooodPrice = bookingDto.getFood().getFooodPrice();
//
//        double totalPrice = ticketPrice + fooodPrice;
//
//
//        FoodDto foodDto = bookingDto.getFood();
//
//        Food food = this.modelMapper.map(foodDto, Food.class);
//        Food savedFood = this.foodRepo.save(food);
////                    Food food1= new Food();                  //
////                    food1.setFoodName(food.getFoodName());    //may be not necessary
////                    food1.setFooodPrice(food.getFooodPrice()); //

        //       Booking booking = this.modelMapper.map(bookingDto, Booking.class)


        User user = userRepo.findByEmail(bookingDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email", "emailName", bookingDto.getEmail()));
        booking.setUser(user);


        Movie movie = movieRepo.findByMovieName(bookingDto.getMovieName())
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "withMovieName", bookingDto.getMovieName()));
        booking.setMovie(movie);

        Theatre theatre = theatreRepo.findByTheaterName(bookingDto.getTheaterName())
                .orElseThrow(() -> new ResourceNotFoundException("Theatre", "Id", bookingDto.getTheaterName()));
        booking.setTheatre(theatre);

        Shows shows = this.utilityMethods.createShows(bookingDto);
        booking.setShows(shows);

        //     shows.getAvailableSeats().contains(bookingDto.getSeats().stream())

        boolean availableSeats = new HashSet<>(shows.getAvailableSeats()).containsAll(bookingDto.getBookedSeats());

        if (availableSeats) {
            shows.getAvailableSeats().removeAll(bookingDto.getBookedSeats());
        } else {
            throw new ResourceNotFoundException("Any one of the seat is not available please try again,available seats are" + shows.getAvailableSeats());
        }
        booking.setBokedSeats(bookingDto.getBookedSeats());

        Integer totalPrice = utilityMethods.calculateTotalPrice(bookingDto);
        if (bookingDto.getTotalPrice().intValue() == utilityMethods.calculateTotalPrice(bookingDto)) {
            booking.setTotalPrice(bookingDto.getTotalPrice());
        } else {
            throw new ResourceNotFoundException("Total price mismatch");
        }
        if (bookingDto.getBookedSeatsPrice().intValue() == utilityMethods.calculateSeatPrice(bookingDto)) {
            booking.setBookedSeatsPrice(bookingDto.getBookedSeatsPrice());
        } else {
            throw new ResourceNotFoundException("Booked Seat price mismatch");
        }

//        Payment payment = utilityMethods.createPayment(bookingDto);
//        booking.setPayment(payment);

        Food foodItem = utilityMethods.saveFoodItem(bookingDto);
        booking.setFood(foodItem);
        booking.setShowDate(bookingDto.getShowDate());

        Booking booking1 = bookingRepo.save(booking);
        return bookingToBookingDto(booking1);
    }

    @Override
    public List<BookingDto> getAllBookedTickets() {

               List<Booking> bookingList= bookingRepo.findAll();
        if (bookingList.isEmpty()){
            return new ArrayList<BookingDto>();
        }
        else {
            List<BookingDto> bookingTicketDtoList = bookingList.stream()
                    .map(b1 -> bookingToBookingDto(b1))
                    .collect(Collectors.toList());
            return bookingTicketDtoList;
        }

    }

    @Override
    public List<BookingDto> getTicketsByUserEmail(String userEmail) {
        List<Booking> listOfticketByUserEmail = bookingRepo.findByUserEmail(userEmail);
        if (listOfticketByUserEmail.isEmpty()){
            throw new ResourceNotFoundException("Ticket","user email",userEmail);
        }
        else {
            return listOfticketByUserEmail.stream()
                    .map(b -> bookingToBookingDto(b))
                    .collect(Collectors.toList());
        }
    }

//            booking.setUser(user);
//            booking.setMovie(movie);
//            booking.setTheatre(theatre);
         //   booking.setFood(savedFood);
         //   booking.setTotalPrice(totalPrice);


//        User user=this.userRepo.findById((bookingDto.getUser().getId()))
//                .orElseThrow(()->new ResourceNotFoundException("User","Id",bookingDto.getUser().getId()));
//
//       Movie movie = this.movieRepo.findById(bookingDto.getMovie().getId())
//               .orElseThrow(()->new ResourceNotFoundException("Movie","Id",bookingDto.getMovie().getId()));
//
//       Theatre theatre =this.theatreRepo.findById(bookingDto.getTheatre().getId())
//               .orElseThrow(()->new ResourceNotFoundException("Theatre","Id",String.valueOf(bookingDto.getTheatre().getId()) ));
//
//
//                 Booking booking= modelMapper.map(bookingDto, Booking.class);
//                 booking.setUser(user);
//                 booking.setMovie(movie);
//  //               booking.setTheatre(theatre);
//


        //     return this.modelMapper.map(booking,BookingDto.class) ;
        //  r

     //   BookingDto bookingDto1 = this.modelMapper.map(booking1, BookingDto.class);


    private  BookingDto bookingToBookingDto(Booking booking){

        BookingDto bookingDto= new BookingDto();
            bookingDto.setBookingId(booking.getBookingId());
            bookingDto.setFname(booking.getUser().getFname());
            bookingDto.setEmail(booking.getUser().getEmail());
            bookingDto.setMovieName(booking.getMovie().getMovieName());
            bookingDto.setMoviePoster(booking.getMovie().getMoviePoster());
            bookingDto.setTheaterName(booking.getTheatre().getTheaterName());
            bookingDto.setTheaterLocation(booking.getTheatre().getTheaterLocation());
            bookingDto.setShowtime(String.valueOf(booking.getShows().getShowTime()));
            bookingDto.setShowDate(booking.getShowDate());
            bookingDto.setFood(booking.getFood().getFoodName());
            bookingDto.setBookedSeats(booking.getBokedSeats());
            bookingDto.setFoodPrice(Double.valueOf(booking.getFood().getFoodPrice()));
            bookingDto.setBookedSeatsPrice(booking.getBookedSeatsPrice());
            bookingDto.setTotalPrice(booking.getTotalPrice());

        return bookingDto;
    }





//    public BookResponseDto bookingDtoToBookingResponseDto(BookingDto bookingDto) {
////        this.bookingDtoToBookingResponseDto();
////        BookResponseDto bookResponseDto= new BookResponseDto();
//
//        BookResponseDto bookingResponseDtoRes = new BookResponseDto();
//
//        bookingResponseDtoRes.setBookingId(bookingDto.getBookingId());
//        bookingResponseDtoRes.setFname(bookingDto.getUser().getFname());
//        bookingResponseDtoRes.setEmail(bookingDto.getUser().getEmail());
//        bookingResponseDtoRes.setMovieName(bookingDto.getMovie().getMovieName());
//        bookingResponseDtoRes.setMoviePoster(bookingDto.getMovie().getMoviePoster());
//        bookingResponseDtoRes.setTheaterName(bookingDto.getTheatre().getTheaterName());
//        bookingResponseDtoRes.setTheaterLocation(bookingDto.getTheatre().getTheaterLocation());
//        bookingResponseDtoRes.setSeats(bookingDto.getSeats());
//        bookingResponseDtoRes.setShowDate(bookingDto.getShowDate());
//        bookingResponseDtoRes.setShowtime(bookingDto.getShowtime());
//        bookingResponseDtoRes.setFoodName(bookingDto.getFood().getFoodName());
//        bookingResponseDtoRes.setFooodPrice(bookingDto.getFood().getFooodPrice());
//        bookingResponseDtoRes.setTicketPrice(bookingDto.getTicketPrice());
//        bookingResponseDtoRes.setTotalPrice(bookingDto.getTotalPrice());
//
//
//
//
//        return bookingResponseDtoRes;
//
//
//    }
}
