package com.example.quatro_to.func;

import com.example.quatro_to.model.Floor;
import com.example.quatro_to.repository.FloorRepository;
import com.example.quatro_to.service.FloorService;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;


@SpringBootTest
@RunWith(SpringRunner.class)
public class FloorServiceFuncTest {

    @Autowired
    private FloorService floorService;
    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void verifyUpdate(){

        Floor floor = Floor.builder().number(1).build();
        Floor savedFloor = floorService.save(floor);
        Floor expected = Floor.builder()
                .id(savedFloor.getId())
                .number(22)
                .build();

        Floor actual = floorService.update(expected, savedFloor.getId());

        assertThat(expected.getId(), is(actual.getId()));
        assertThat(expected.getNumber(), is(actual.getNumber()));
    }

    @Test
    public void verifyFindById(){
        Floor floor = Floor.builder().number(1).build();
        Floor expected = floorRepository.save(floor);
        Floor actual = floorService.findById(expected.getId());

        Assert.assertEquals(expected.getId(),actual.getId());
        Assert.assertEquals(expected.getNumber(),actual.getNumber());
    }

    @Test
    public void verifyFindAll(){
        floorRepository.saveAll(Arrays.asList(
                Floor.builder().number(12).build(),
                Floor.builder().number(13).build()
        ));

        Set<Floor> actual= floorService.findAll();
        assertThat(actual.size(),is(2));
    }

    @Test
    public void verifySave(){
        Floor floor = Floor.builder().number(44).build();
        Floor expected = floorService.save(floor);

        Optional<Floor> actualFloor = floorRepository.findById(expected.getId());

        Assert.assertTrue( actualFloor.isPresent());
    }

    @Test
    public void verifyDeleteById(){
        Floor saved = floorService.save(Floor.builder().number(13).build());
        floorService.delete(saved.getId());
        Optional<Floor> expected = floorRepository.findByNumber(13);

        Assert.assertFalse(expected.isPresent());
    }

    @Test
    public void verifyFindByNUmber(){
        Floor floor = Floor.builder().number(56).build();
        Floor expected = floorRepository.save(floor);
        Floor actual = floorService.findByNumber(expected.getNumber());

        Assert.assertEquals(expected.getNumber(),actual.getNumber());
    }

}
