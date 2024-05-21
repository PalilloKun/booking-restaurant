package com.restaurant.booking.service;

import com.restaurant.booking.entity.Booking;
import com.restaurant.booking.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    /**
     * Method to call Respository and create new Booking
     * @param booking
     */
    public void addBooking(Booking booking) {

        bookingRepository.save(booking);

    }

    /**
     * Method to call Respository and get all Bookings, get all bookings in specific date
     * @param date
     * @return
     */
    public List<Booking> getBookingsForDay(LocalDate date) {

        List<Booking> findAll = StreamSupport.stream(bookingRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return findAll.stream()
                .filter(booking -> booking.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Booking> getAll(){
        return (List<Booking>) bookingRepository.findAll();
    }
}
