package com.practise.dto;

import lombok.Data;

@Data
public class BookingRequestDto {
    private int shipId;
    private String passengerName;
    private int age;
}
