package com.restaurant.booking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.restaurant.booking.dto.ResponseBooking;
import com.restaurant.booking.entity.Booking;
import com.restaurant.booking.service.AvailableTableService;
import com.restaurant.booking.service.BookingService;
import com.restaurant.booking.service.ClientService;
import com.restaurant.booking.utils.Utils;
import io.muserver.ContentTypes;
import io.muserver.MuRequest;
import io.muserver.MuResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class BookingController {


    @Autowired
    private BookingService bookingService;

    @Autowired
    private AvailableTableService availableTableService;

    private Gson gson = new Gson();
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    /**
     * Method to create a booking, this method validate if the available tables are enough to booking
     * the specif tableSize input, and also validate the same Client can't book in the same hour again
     * @param request
     * @param response
     * @param pathParams
     */
    public void createBookingHandler(MuRequest request, MuResponse response, Map<String, String> pathParams) {
        try {

            Booking booking = objectMapper.readValue(request.readBodyAsString(), Booking.class);

            //Checking the total available tables

            int totalAvailableTables = availableTableService.getAvailableTables();

            if ((totalAvailableTables - booking.getTableSize()) > 0) {

                if(validateExistingBooking(booking)){
                    ResponseBooking responseBooking = ResponseBooking.builder().status(400)
                            .message("you cant booking another table at the same time").build();

                    response.write(Utils.convertToString(responseBooking));

                }else{
                    bookingService.addBooking(booking);

                    ResponseBooking responseBooking = ResponseBooking.builder().status(200)
                            .message("Booking created successfully").build();

                    response.write(Utils.convertToString(responseBooking));
                }

            }else{
                ResponseBooking responseBooking = ResponseBooking.builder().status(500)
                        .message("Impossible to reserve {} "+booking.getTableSize()).build();
                response.write(Utils.convertToString(responseBooking));
            }


        } catch (Exception e) {
            e.printStackTrace();
            response.write("Invalid booking data");
        }
    }

    /**
     * Method to get all booking on a specific day
     * @param request
     * @param response
     * @param pathParams
     */
    public void getBookingsForDay(MuRequest request, MuResponse response, Map<String, String> pathParams) {
        try {
            String dateStr = pathParams.get("date");
            log.error("data",dateStr);
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE.ISO_DATE);
            List<Booking> bookings = bookingService.getBookingsForDay(date);


            if( bookings.size() > 0 ){

                ResponseBooking responseBooking = ResponseBooking.builder().status(200)
                        .message("")
                        .bookings(bookings)
                        .build();
                response.write(Utils.convertToString(responseBooking));
            }else{
                ResponseBooking responseBooking = ResponseBooking.builder().status(404)
                        .message("No records found")

                        .build();
                response.write(Utils.convertToString(responseBooking));
            }



        } catch (Exception e) {
            e.printStackTrace();
            response.status(400);
        }
    }

    /**
     * Method to get all bookings
     * @param request
     * @param response
     * @param pathParams
     * @throws JsonProcessingException
     */
    public void getAllBookings(MuRequest request, MuResponse response, Map<String, String> pathParams)
            throws JsonProcessingException {
        List<Booking> bookings = bookingService.getAll();
        response.write(Utils.convertToString(bookings));
    }

    /**
     * Method to validate if the Client has booked at the same time again
     * @param booking
     * @return
     */
     boolean validateExistingBooking(Booking booking){

        List<Booking> bookingList = bookingService.getAll();
        log.error("bookingList total {}",bookingList.size());
        return bookingList
                .stream()
                .anyMatch(f -> f.getDateTime().isAfter(booking.getDateTime())
                        && f.getDateTime().plusHours(2).isBefore(booking.getDateTime()));

    }
}
