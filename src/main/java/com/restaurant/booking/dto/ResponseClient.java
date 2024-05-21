package com.restaurant.booking.dto;

import com.restaurant.booking.entity.Booking;
import com.restaurant.booking.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseClient {

    private int status;
    private String message;

    private List<Client> clients;
}
