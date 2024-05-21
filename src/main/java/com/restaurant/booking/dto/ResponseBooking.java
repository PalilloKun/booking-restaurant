package com.restaurant.booking.dto;

import com.restaurant.booking.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseBooking {

    private int status;

    private String message;

    private List<Booking> bookings;
}
