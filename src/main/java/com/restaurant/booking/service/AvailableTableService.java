package com.restaurant.booking.service;


import com.restaurant.booking.entity.AvailableTable;
import com.restaurant.booking.repository.AvailableTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AvailableTableService {

    @Autowired
    private AvailableTableRepository availableTableRepository;

    /**
     * Methodo to call Respository and update/insert the initial value for tables
     * @param availableTable
     */
    public void updateAvailableTable(AvailableTable availableTable){

        log.error("availableTable 1 {}",availableTable);
        availableTableRepository.save(availableTable);

    }

    /**
     * Method to get the current available tables
     * @return
     */
    public int getAvailableTables(){
        return availableTableRepository
                .findById(1L)
                .map(a -> a.getTotal())
                .orElseThrow();
    }
}
