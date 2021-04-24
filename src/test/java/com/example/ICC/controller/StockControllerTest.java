package com.example.ICC.controller;

import com.example.ICC.model.request.QuotesRequest;
import com.example.ICC.model.request.StockRequest;
import com.example.ICC.model.response.StockResponse;
import com.example.ICC.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    StockService service;

    @Test
    public void createdWithSucessAndReturn201() throws Exception {
        //List<Double> quotes = List.of(15.1,15.2,15.3);
        List<Double> quotes = new ArrayList<>();
        StockRequest stockRequest = new StockRequest("StockI", quotes);
        String request = objectMapper.writeValueAsString(stockRequest);
        String response = objectMapper.writeValueAsString(stockRequest);
        URI uri =  UriComponentsBuilder.fromUriString("/stock").build().toUri();
        String location =  UriComponentsBuilder.fromUriString("/stock?name=StockI").build().toUriString();
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.header().string("location", location));
    }

    @Test
    public void FailWithExistingNameAndReturn400() throws Exception {
        List<Double> quotes2 = new ArrayList<>();
        StockRequest stockRequest2 = new StockRequest("StockI", quotes2);
        service.create(stockRequest2);

        //List<Double> quotes = List.of(15.1,15.2,15.3);
        List<Double> quotes = new ArrayList<>();
        StockRequest stockRequest = new StockRequest("StockI", quotes);
        String request = objectMapper.writeValueAsString(stockRequest);
        String response = objectMapper.writeValueAsString(stockRequest);
        URI uri =  UriComponentsBuilder.fromUriString("/stock").build().toUri();
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void updatedStockWithSuccess200() throws Exception {

        StockRequest stockRequest = new StockRequest("StockI", List.of(15.1,15.2,15.3));
        service.create(stockRequest);
        QuotesRequest quotesRequest = new QuotesRequest(List.of(15.1,15.2,15.3, 2.0));
        String request = objectMapper.writeValueAsString(quotesRequest);
        StockResponse stockResponse =   new StockResponse(stockRequest.toStock());
        stockResponse.setQuotes(List.of(15.1,15.2,15.3,15.1,15.2,15.3, 2.0));
        String response = objectMapper.writeValueAsString(stockResponse);
        URI uri =  UriComponentsBuilder.fromUriString("/stock/StockI").build().toUri();
        mockMvc.perform(MockMvcRequestBuilders.patch(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }
    @Test
    public void deleteAndReturn204() throws Exception {

            StockRequest stockRequest = new StockRequest("StockI", List.of(15.1,15.2,15.3));
            service.create(stockRequest);
            URI uri =  UriComponentsBuilder.fromUriString("/stock/StockI").build().toUri();
            mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

}