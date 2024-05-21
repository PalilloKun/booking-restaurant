package com.restaurant.booking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.restaurant.booking.dto.ResponseClient;
import com.restaurant.booking.entity.Booking;
import com.restaurant.booking.entity.Client;
import com.restaurant.booking.service.ClientService;
import com.restaurant.booking.utils.Utils;
import io.muserver.MuRequest;
import io.muserver.MuResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());;

    /**
     * Method to create new Client
     * @param request
     * @param response
     * @param pathParams
     * @throws IOException
     */
    public void createClient(MuRequest request, MuResponse response, Map<String, String> pathParams)
            throws IOException {
        Client client = objectMapper.readValue(request.readBodyAsString(), Client.class);
        clientService.createClient(client);
        log.info("user created successfully");

        ResponseClient responseClient = ResponseClient.builder()
                .status(200)
                .message("Client created successfully")
                .build();
        response.write(Utils.convertToString(responseClient));
    }

    /**
     * Method to get Client by Id from Service
     * @param request
     * @param response
     * @param pathParams
     * @throws JsonProcessingException
     */
    public void getClientById(MuRequest request, MuResponse response, Map<String, String> pathParams) throws JsonProcessingException {

        Long byId = Long.valueOf(pathParams.get("byId"));
        Client client =  clientService.getClientById(byId);

        log.info("here {}",client);

        List<Client> lstClient = new ArrayList<>();
        lstClient.add(client);
        ResponseClient responseClient = ResponseClient.builder()
                .message("")
                .clients(lstClient).build();

        response.write(Utils.convertToString(responseClient));

    }
}
