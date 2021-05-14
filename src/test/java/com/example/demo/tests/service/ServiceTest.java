package com.example.demo.tests.service;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.service.Service3;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

public class ServiceTest {

    @InjectMocks
    private Service3 service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetResponse() throws Exception {
        assertEquals(service.createName("sri","harsha").getResponse(), "sri harsha");

    }


}
