package com.practise.controller;

import com.practise.dto.BookingDto;
import com.practise.entity.BookingEntity;
import com.practise.service.BookingService;
import com.practise.service.ShipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @Mock
    private ShipService shipService;

    private MockHttpSession httpSession;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        httpSession = new MockHttpSession();
        bookingController.httpSession = httpSession;
    }

    @Test
    public void getBookingDetails_whenUserIsLoggedIn() {
        httpSession.setAttribute("user", "testUser");
        when(shipService.getShipDetails(anyInt())).thenReturn(new BookingDto());

        ModelAndView modelAndView = bookingController.getBookingDetails(1);

        assertEquals("booking", modelAndView.getViewName());
        verify(shipService, times(1)).getShipDetails(anyInt());
    }

    @Test
    public void getBookingDetails_whenUserIsNotLoggedIn() {
        ModelAndView modelAndView = bookingController.getBookingDetails(1);

        assertEquals("login", modelAndView.getViewName());
        assertEquals(true, modelAndView.getModel().get("loginError"));
    }

@Test
public void processPayment_whenUserIsLoggedIn() {
    httpSession.setAttribute("user", "testUser");

    BookingDto bookingDto = new BookingDto();
    bookingDto.setShipId(1);
    bookingDto.setPassengerName("John Doe");
    bookingDto.setAge(30);

    when(bookingService.saveBooking(anyInt(), anyString(), anyInt(), anyBoolean())).thenReturn(true);

    ModelAndView modelAndView = bookingController.processPayment(bookingDto);

    assertEquals("redirect:/viewTickets", modelAndView.getViewName());
    verify(bookingService, times(1)).saveBooking(1, "John Doe", 30, true);
}
    @Test
    public void processPayment_whenUserIsNotLoggedIn() {
        ModelAndView modelAndView = bookingController.processPayment(new BookingDto());

        assertEquals("login", modelAndView.getViewName());
        assertEquals(true, modelAndView.getModel().get("loginError"));
    }

    @Test
    public void fetchTicketPage_whenUserIsLoggedIn() {
        httpSession.setAttribute("user", "testUser");
        when(bookingService.fetchTickets()).thenReturn(Collections.singletonList(new BookingEntity()));

        ModelAndView modelAndView = bookingController.fetchTicketPage();

        assertEquals("ticket", modelAndView.getViewName());
        verify(bookingService, times(1)).fetchTickets();
    }

    @Test
    public void fetchTicketPage_whenUserIsNotLoggedIn() {
        ModelAndView modelAndView = bookingController.fetchTicketPage();

        assertEquals("login", modelAndView.getViewName());
        assertEquals(true, modelAndView.getModel().get("loginError"));
    }
}