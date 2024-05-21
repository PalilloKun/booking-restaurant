package com.restaurant.booking.config;

import com.restaurant.booking.controller.BookingController;
import com.restaurant.booking.controller.ClientController;
import com.restaurant.booking.controller.ReservedTablesController;
import io.muserver.Method;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitMuServer {

    @Autowired
    private BookingController bookingController;

    @Autowired
    private ClientController clientController;

    @Autowired
    private ReservedTablesController reservedTablesController;

    @PostConstruct
    public void startServer() {
        MuServer server = MuServerBuilder.httpServer()
                .withHttpPort(8080)
                .addHandler(Method.GET, "/", (request, response, pathParams) -> {
                    response.write("Hello, world");
                })
                .addHandler(Method.POST, "/bookings/",
                        bookingController::createBookingHandler)
                .addHandler(Method.GET, "/bookings/{date}",
                        bookingController::getBookingsForDay)
                .addHandler(Method.GET, "/bookings/",
                        bookingController::getAllBookings)
                .addHandler(Method.POST, "/clients/",
                        clientController::createClient)
                .addHandler(Method.GET, "/clients/{byId}",
                        clientController::getClientById)
                .addHandler(Method.POST, "/tables/",
                        reservedTablesController::updateTotalTables)

                .start();
        log.info("Started server at " + server.uri());
    }
}
