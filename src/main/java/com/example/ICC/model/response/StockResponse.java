package com.example.ICC.model.response;

import com.example.ICC.model.Quotes;
import com.example.ICC.model.Stock;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StockResponse {

    @JsonProperty
    private final String name;
    @JsonProperty
    private List<Double> quotes;

    public StockResponse(Stock stock) {
        this.name = stock.getName();
        this.quotes = stock.getQuotes().stream().map(Quotes::getQuote).collect(Collectors.toList());
    }

    public List<Double> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Double> quotes) {
        this.quotes = quotes;
    }
}
