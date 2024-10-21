package com.practise.controller;

import com.practise.dto.BookingDto;
import com.practise.entity.BookingEntity;
import com.practise.service.BookingService;
import com.practise.service.ShipService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    ShipService shipService;

    @Autowired
    HttpSession httpSession;


    @GetMapping("/book-ship")
    public ModelAndView getBookingDetails(@RequestParam("shipId") int shipId) {
        if (httpSession.getAttribute("user") != null) {
            BookingDto shipDetails = shipService.getShipDetails(shipId);
            ModelAndView modelAndView = new ModelAndView("booking");
            modelAndView.addObject("booking", shipDetails);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }

    @GetMapping("/payment")
    public ModelAndView cardPayment(BookingDto booking
//                @RequestParam("shipId") int shipId,
//                                        @RequestParam String passengerName,
//                                        @RequestParam int age
    ) {
        if (httpSession.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView("payment");
            modelAndView.addObject("booking", booking);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }

    @PostMapping("/processPayment")
    public ModelAndView processPayment(BookingDto booking) {
        if (httpSession.getAttribute("user") != null) {
            bookingService.saveBooking(booking.getShipId(), booking.getPassengerName(), booking.getAge(),true);
            return new ModelAndView("redirect:/viewTickets");
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }

    @GetMapping("/viewTickets")
    public ModelAndView fetchTicketPage() {
        if (httpSession.getAttribute("user") != null) {
            List<BookingEntity> bookingEntities = bookingService.fetchTickets();
            ModelAndView modelAndView = new ModelAndView("ticket");
            modelAndView.addObject("bookings", bookingEntities);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }

    @GetMapping("/cancelTicket")
    public String cancelTicket(@RequestParam("bookingId") int bookingId, Model model) {
        bookingService.deleteBooking(bookingId);
        List<BookingEntity> bookingEntities = bookingService.fetchTickets();
        model.addAttribute("bookings", bookingEntities);
        return "redirect:/viewTickets";
    }

    @GetMapping("/printTicket")
    public ModelAndView printTicket(@RequestParam("bookingId") int bookingId) {
        if (httpSession.getAttribute("user") != null) {
            BookingEntity bookingEntity = bookingService.fetchTicketById(bookingId);
            ModelAndView modelAndView = new ModelAndView("print");
            modelAndView.addObject("booking", bookingEntity);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }
}