package com.example.demo.tests.controller;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.controller.ServiceController;
import com.example.demo.service.Service3;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ServiceController.class})
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Value("${service.route.path}")
    private String apiUrl;

    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    @MockBean
    Service3 service;

    @BeforeClass
    public static void setUp() {

    }

    @Test
    public void testCreateName() throws Exception {


        String requestString = "{\"Name\":\"sri\",\"Surname\":\"harsha\"}";

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("sri harsha");

        given(this.service.createName(anyString(), anyString())).willReturn(responseDTO);

        mvc.perform(post("http://localhost:8083/" + apiUrl + "/name")
                .contentType(MediaType.APPLICATION_JSON).content(requestString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", is("sri harsha")));


    }

    @Test
    public void testWithWrongInput() throws Exception {

        String requestString = "{\"Name\":\"\",\"Surname\":\"harsha\"}";
//
//        ResponseDTO responseDTO = new ResponseDTO();
//        responseDTO.setResponse("sri harsha");

       // given(this.service.createName(anyString(), anyString())).willReturn(responseDTO);

        MvcResult mvcResult=mvc.perform(post("http://localhost:8083/" + apiUrl + "/name")
                .contentType(MediaType.APPLICATION_JSON).content(requestString))
                .andExpect(status().is4xxClientError()).andReturn();

        System.out.println(mvcResult.getResolvedException());
        System.out.println(mvcResult.getResolvedException().getMessage());

        assertTrue(mvcResult.getResolvedException() instanceof MethodArgumentNotValidException);
        assertTrue(mvcResult.getResolvedException().getMessage().contains("size should be between 1 and 50"));

    }

}