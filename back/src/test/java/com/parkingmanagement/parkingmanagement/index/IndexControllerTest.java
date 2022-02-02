package com.parkingmanagement.parkingmanagement.index;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkingmanagement.parkingmanagement.controller.IndexController;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.service.OccupationService;
import com.parkingmanagement.parkingmanagement.service.OccupationServiceImplementation;
import com.parkingmanagement.parkingmanagement.service.ParkingSpaceServiceImplementation;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

//    @BeforeEach
//    public void setup(){
//        RestAssuredMockMvc.standaloneSetup(this.indexController);
//    }

    @Test
    public void itShouldReturnOk() throws Exception{
        FillParkingSpaceDto parkingSpaceFilled = new FillParkingSpaceDto(
                Long.valueOf(1),
                new Car("abc-1234", "HondaCivic 2009"),
                "525.721.680-90",
                LocalTime.of(13, 00)
        );

        mockMvc.perform(put("/api/fillParkingSpace")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingSpaceFilled)))
                .andExpect(status().isOk());
    }


}
