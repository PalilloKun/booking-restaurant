package com.restaurant.booking.repository;

import com.restaurant.booking.entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {


    public List<Booking> findByCustomerName(String customerName);
}
