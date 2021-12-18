package com.example.quatro_to.controller;

import com.example.quatro_to.converter.FloorConverter;
import com.example.quatro_to.dto.FloorDto;
import com.example.quatro_to.model.Floor;
import com.example.quatro_to.service.FloorService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = FloorController.class)
public class FloorControllerTest extends  BaseControllerTest{

    @MockBean
    private  FloorService floorService;
    @MockBean
    private  FloorConverter floorConverter;

    @Test
    public void save() throws Exception {

        FloorDto floorDto = FloorDto.builder().number(1).build();
        String reqJson = objectMapper.writeValueAsString(floorDto);

        when(floorConverter.toFloor(any(FloorDto.class))).thenReturn(Floor.builder().build());
        when(floorService.save(any(Floor.class))).thenReturn(Floor.builder().build());
        when(floorConverter.toFloorDto(any(Floor.class))).thenReturn(FloorDto.builder().number(1).build());

        mockMvc.perform(post("/floors").content(reqJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", Matchers.is(1)));
    }


    @Test
    public void getById() throws Exception{

        when(floorService.findById(any(Long.class))).thenReturn(Floor.builder().build());
        when(floorConverter.toFloorDto(any(Floor.class))).thenReturn(FloorDto.builder().number(1).build());

        mockMvc.perform(get("/floors/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number",Matchers.is(1)));
    }

    @Test
    public void deleteHappy() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/floors/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateHappy() throws Exception{

        FloorDto floorDto = FloorDto.builder().number(1).build();
        String reqJson = objectMapper.writeValueAsString(floorDto);

        when(floorConverter.toFloorDto(any())).thenReturn(floorDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/floors/1").content(reqJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number",Matchers.is(1)));
    }



}