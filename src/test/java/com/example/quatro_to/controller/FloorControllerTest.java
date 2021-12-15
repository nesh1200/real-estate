package com.example.quatro_to.controller;

import com.example.quatro_to.converter.FloorConverter;
import com.example.quatro_to.service.FloorService;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(value = FloorController.class)
public class FloorControllerTest extends  BaseControllerTest{

    @Mock
    private  FloorService floorService;
    @Mock
    private  FloorConverter floorConverter;

    @Test
    public void save() {

        mockMvc.perform(post("/floors"))
                .andExpect()
    }
}