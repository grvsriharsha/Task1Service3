package com.example.demo.service;

import com.example.demo.DTO.RequestDTO;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.LogMethodParam;
import com.example.demo.controller.ServiceController;
import com.example.demo.exceptions.JsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Service3 {

    private static final Logger LOG = Logger.getLogger(Service3.class.getName());

    @LogMethodParam
    public ResponseDTO createName(String name, String surname) {
        LOG.log(Level.INFO, "i am in service3 with name:" + name + " surname:" +surname);
        printData(name, surname);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse(name + " " + surname);
        return responseDTO;
    }

    public void printData(String name, String surname) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setName(name);
        requestDTO.setSurname(surname);
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = mapper.writeValueAsString(requestDTO);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            throw new JsonException("Invalid data name: " + requestDTO.getName() + " surname: " + requestDTO.getSurname());
        }
    }
}
