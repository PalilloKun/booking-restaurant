package com.restaurant.booking.repository;

import com.restaurant.booking.entity.AvailableTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableTableRepository  extends CrudRepository<AvailableTable, Long> {
}
