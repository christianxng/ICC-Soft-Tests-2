package com.example.ICC.model.request;

import com.example.ICC.model.Stock;
import com.example.ICC.validator.UniqueName;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class StockRequest {

    @JsonProperty
    @NotBlank(message = "must not be blank")
    @UniqueName
    private final String name;
    @JsonProperty
    private final List<Double> quotes;

    public StockRequest(String name, List<Double> quotes) {
        this.name = name;
        this.quotes = quotes;
    }

    public List<Double> getQuotes() {
        return quotes;
    }
    public Stock toStock(){
        return new Stock(this.name);
    }

}
