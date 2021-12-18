package com.example.quatro_to.controller.integration;

import com.example.quatro_to.dto.FloorDto;
import com.example.quatro_to.model.Floor;
import com.example.quatro_to.repository.FloorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FloorControllerIntTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void saveFloor() throws Exception {

        FloorDto floorDto = FloorDto.builder().number(55).build();
        String jsonRequest = objectMapper.writeValueAsString(floorDto);

        RestAssured.given().contentType(ContentType.JSON)
                .body(jsonRequest)
                .when()
                .post("http://localhost:8080/floors")
                .then()
                .statusCode(200)
                .body("number", Matchers.equalTo(55));
    }

    @Test
    public void updateFloor() throws Exception{

        Floor floor = floorRepository.save(Floor.builder().number(54).build());
        FloorDto floorDto = FloorDto.builder().id(1L).number(77).build();
        String reqJson = objectMapper.writeValueAsString(floorDto);

        RestAssured.given().contentType(ContentType.JSON)
                .body(reqJson)
                .when()
                .put("http://localhost:8080/floors/1")
                .then()
                .statusCode(200)
                .body("id",Matchers.equalTo(1))
                .body("number", Matchers.equalTo(77));
    }

    @Test
    public void findById() throws Exception{
        Floor floor = floorRepository.save(Floor.builder().number(54).build());

        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/floors/id/1")
                .then()
                .statusCode(200)
                .body("id",Matchers.equalTo(1))
                .body("number", Matchers.equalTo(54));
    }


    @Test
    public void findByNUmber() throws Exception{
        Floor floor = floorRepository.save(Floor.builder().number(54).build());

        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/floors/54")
                .then()
                .statusCode(200)
                .body("number", Matchers.equalTo(54));
    }

}
