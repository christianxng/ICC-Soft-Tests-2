package com.example.ICC.model.response;

import com.example.ICC.model.Quotes;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteResponse {

    @JsonProperty
    private final Double quotes;

    public QuoteResponse(Quotes quotes) {
        this.quotes = quotes.getQuote();
        //this.quotes = quotes.stream().map(Quotes::getQuote).collect(Collectors.toList());
    }
}
