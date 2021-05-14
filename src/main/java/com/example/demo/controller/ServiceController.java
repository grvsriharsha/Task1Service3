package com.example.demo.controller;

import com.example.demo.DTO.RequestDTO;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.service.Service3;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("${service.route.path}")
@Api(value = "MicroService3")
public class ServiceController {

    @Autowired
    private Service3 service3;

    private static final Logger LOG = Logger.getLogger(ServiceController.class.getName());

    @PostMapping("/name")
    @ApiOperation(value = "prints and concat names the name and surname")
    public ResponseDTO createName(@Validated @RequestBody RequestDTO requestDTO) {
        LOG.log(Level.INFO, "i am in controller3 with name:" + requestDTO.getName() + " surname:" + requestDTO.getSurname());
        return service3.createName(requestDTO.getName(), requestDTO.getSurname());
    }


}
