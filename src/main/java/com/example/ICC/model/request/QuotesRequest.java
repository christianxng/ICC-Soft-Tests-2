package com.example.ICC.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuotesRequest {

    @JsonProperty
    private List<Double> quotes;

    @JsonCreator
    public QuotesRequest(List<Double> quotes) {
        this.quotes = quotes;
    }

    public List<Double> getQuotes() {
        return quotes;
    }


    public void setQuotes(List<Double> quotes) {
        this.quotes = quotes;
    }
}
