package com.restaurant.booking.utils;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.restaurant.booking.entity.Booking;

import java.util.List;

public  class Utils {

    /**
     * Method to convert Java Object to string
     * @param t
     * @return
     * @param <T>
     * @throws JsonProcessingException
     */

    public static <T> String convertToString(T t) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String s = objectMapper.writeValueAsString(t);

        return s;
    }

    /**
     * Method to convert List of Java Object to string
     * @param t
     * @return
     * @param <T>
     * @throws JsonProcessingException
     */

    public static <T> String convertListToString(List<T> t) throws JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String s = objectMapper.writeValueAsString(t);

        return s;
    }


}
