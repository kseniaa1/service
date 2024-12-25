package com.example.tests.controller;

import com.example.tests.exception.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.tests.dto.DataRequestDTO;
import com.example.tests.dto.DataResponseDTO;
import com.example.tests.exception.DataInvalidException;
import com.example.tests.service.DataService;

import lombok.SneakyThrows;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = DataController.class) // для теста контроллеров
class DataControllerTest {

    @MockBean
    DataService dataService;

    @Autowired
    MockMvc mockMvc;

    @SneakyThrows
    @Test
    void Create_ReturnStatusBadRequest_ServiceThrowsException() {
        DataRequestDTO dataNameNULL = new DataRequestDTO(null);

        Mockito.doThrow(DataInvalidException.class)
                .when(dataService)
                .create(dataNameNULL);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/data")
                        .content("""
                                    {
                                        "name": null
                                    }
                                 """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest());
    }

    @SneakyThrows
    @Test
    void Create_Return_StatusOkAndId_ServiceReturnId() {
        DataRequestDTO data = new DataRequestDTO("valid name");

        Mockito.doReturn(1L)
                .when(dataService)
                .create(data);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/data")
                        .content("""
                                    {
                                        "name": "valid name"
                                    }
                                 """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("1"));
    }

    @SneakyThrows
    @Test
    void findByName_ReturnStatusBadRequest_ServiceThrowsInvalidException() {
        String invalidName = "invalid name";

        Mockito.doThrow(new DataInvalidException("Invalid data"))
                .when(dataService)
                .findByName(invalidName);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/data/name/{dateName}", invalidName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.status().reason("Invalid data"));

    }

    @SneakyThrows
    @Test
    void findByName_ReturnStatusNotFound_ServiceThrowsNotFoundException() {
        String notFoundName = "nonexistent name";

        Mockito.doThrow(new DataNotFoundException("Data not found"))
                .when(dataService)
                .findByName(notFoundName);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/data/name/{dateName}", notFoundName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.status().reason("Data not found"));
    }

    @SneakyThrows
    @Test
    void findByName_ReturnStatusOkAndValidDataResponse_ServiceReturnDataResponse() {
        String validName = "valid name";
        DataResponseDTO dataResponseDTO = new DataResponseDTO(1, validName);

        Mockito.when(dataService.findByName(validName)).thenReturn(dataResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/data/name/{dateName}", validName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Проверяем, что статус 200
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(dataResponseDTO.id()) )
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dataResponseDTO.name()));

    }
}