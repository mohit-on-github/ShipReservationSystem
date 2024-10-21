package com.practise.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class BookingDto {
    private int shipId;
    private String Source;
    private String Destination;
    private String ShipName;
    private String ShipModel;
    private LocalDate dateOfJourney;
    private int price;
    private String  userEmail;
    private String  userName;
    private String passengerName;
    private int age;
    private String mobNo;
}
