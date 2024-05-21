package com.restaurant.booking.service;

import com.restaurant.booking.entity.Booking;
import com.restaurant.booking.entity.Client;
import com.restaurant.booking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    public void createClient(Client client){
        clientRepository.save(client);
    }

    public Client getClientById(Long byId){
        return clientRepository.findById(byId).orElse(new Client());
    }

    public List<Client> getAll(){
        return (List<Client>) clientRepository.findAll();

    }
}
