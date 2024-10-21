package com.practise.service;

import com.practise.entity.BookingEntity;
import com.practise.entity.ShipEntity;
import com.practise.entity.UserEntity;
import com.practise.repository.BookingRepository;
import com.practise.repository.ShipRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    HttpSession httpSession;

    @Autowired
    BookingRepository bookingRepository;

    public Boolean saveBooking(int shipId,String passengerName, int age, boolean paymentStatus){
        BookingEntity booking = new BookingEntity();
       UserEntity user = (UserEntity) httpSession.getAttribute("user");
        ShipEntity ship = shipRepository.findAllByShipId(shipId);
        int availability = ship.getSeatAvailability();
        if(availability>0) {
            ship.setSeatAvailability(ship.getSeatAvailability() - 1);
            booking.setShip(ship);
            booking.setUser(user);
            booking.setPassengerName(passengerName);
            booking.setAge(age);
            booking.setPaymentStatus(paymentStatus);
            bookingRepository.save(booking);
            return true;
        }
        else {
            return false;
        }
    }
    public List<BookingEntity> fetchTickets(){
        UserEntity user = (UserEntity) httpSession.getAttribute("user");
        List<BookingEntity> bookingEntities = bookingRepository.findAllByUser(user);
        return  bookingEntities;
    }
    public BookingEntity fetchTicketById(int bookingId){
        BookingEntity bookingEntity = bookingRepository.findByBookingId(bookingId);
        return bookingEntity;
    }
    public void deleteBooking(int bookingId){
        Optional<BookingEntity> bookingEntity = bookingRepository.findById(bookingId);
        bookingEntity.get().setPaymentStatus(false);
        ShipEntity ship = bookingEntity.get().getShip();
        ship.setSeatAvailability(ship.getSeatAvailability()+1);
        bookingRepository.save(bookingEntity.get());
    }
}

