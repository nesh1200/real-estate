package com.example.quatro_to.service.impl;

import com.example.quatro_to.exception.DuplicateRecordException;
import com.example.quatro_to.exception.ResourceNotFoundException;
import com.example.quatro_to.model.Floor;
import com.example.quatro_to.repository.FloorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FloorServiceTest {

    @Mock
    private FloorRepository floorRepository;

    @InjectMocks
    private FloorServiceImpl floorServiceImpl;

    @Mock
    private Floor floor;

    @Test
    public void verifyFindAll() {
        when(floorRepository.findAll())
                .thenReturn(Collections.emptyList());

        floorServiceImpl.findAll();
        verify(floorRepository,times(1)).findAll();
    }

    @Test
    public void verifySave(){

        when(floorRepository.save(any(Floor.class))).thenReturn(floor);
        Floor floor1 = Floor.builder().build();
        floorServiceImpl.save(floor1);

        verify(floorRepository, times(1)).save(floor1);
    }

    @Test(expected = DuplicateRecordException.class)
    public void verifySaveDuplicateException(){
        when(floorRepository.save(any(Floor.class))).thenThrow(DataIntegrityViolationException.class);

        floorServiceImpl.save(Floor.builder().build());
    }

    @Test
    public void verifyTestByNumber(){
        when(floorRepository.findByNumber(any(Integer.class))).thenReturn(Optional.of(Floor.builder().build()));
        floorServiceImpl.findByNumber(1);

        verify(floorRepository,times(1)).findByNumber(1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyTestByNumberException(){
        when(floorRepository.findByNumber(any(Integer.class))).thenReturn(Optional.empty());
        floorServiceImpl.findByNumber(1);

        verify(floorRepository,times(1)).findByNumber(1);
    }

    @Test
    public void verifyTestById(){
        when(floorRepository.findById(any(Long.class))).thenReturn(Optional.of(Floor.builder().build()));
        floorServiceImpl.findById(1L);

        verify(floorRepository,times(1)).findById(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyTestByIdException(){
        when(floorRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        floorServiceImpl.findById(1L);

        verify(floorRepository,times(1)).findById(1L);
    }

    @Test
    public void verifyDelete(){
        doNothing().when(floorRepository).deleteById(anyLong());
        floorServiceImpl.delete(1L);

        verify(floorRepository, times(1)).deleteById(1L);
    }
}
