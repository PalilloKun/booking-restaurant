package com.restaurant.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.restaurant.booking.dto.ResponseAvailableTable;
import com.restaurant.booking.entity.AvailableTable;
import com.restaurant.booking.entity.Client;
import com.restaurant.booking.service.AvailableTableService;
import com.restaurant.booking.utils.Utils;
import io.muserver.MuRequest;
import io.muserver.MuResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class ReservedTablesController {

    @Autowired
    private AvailableTableService availableTableService;

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());;

    /**
     * Method to insert the initial value of tables
     * @param request
     * @param response
     * @param pathParams
     * @throws IOException
     */
    public void updateTotalTables(MuRequest request, MuResponse response, Map<String, String> pathParams) throws IOException {

        AvailableTable availableTable = objectMapper.readValue(request.readBodyAsString(), AvailableTable.class);
        log.error("availableTable {}",availableTable);

        availableTableService.updateAvailableTable(availableTable);

        ResponseAvailableTable responseAvailableTable = ResponseAvailableTable
                .builder()
                .status(200)
                .message("Number of total table updated successfully")
                .build();

        response.write(Utils.convertToString(responseAvailableTable));

    }
}
