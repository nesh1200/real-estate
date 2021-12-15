package com.example.quatro_to.controller;

import com.example.quatro_to.converter.FloorConverter;
import com.example.quatro_to.dto.FloorDto;
import com.example.quatro_to.model.Floor;
import com.example.quatro_to.service.FloorService;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = FloorController.class)
public class FloorControllerTest extends  BaseControllerTest{

    @Mock
    private  FloorService floorService;
    @Mock
    private  FloorConverter floorConverter;

    @Test
    public void save() throws Exception {

        FloorDto floorDto = FloorDto.builder().number(23).build();
        String reqJson = objectMapper.writeValueAsString(floorDto);

        when(floorConverter.toFloor(any(FloorDto.class))).thenReturn(Floor.builder().build());
        //when(floorDto) TODO

        mockMvc.perform(post("/floors").content(reqJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }
}