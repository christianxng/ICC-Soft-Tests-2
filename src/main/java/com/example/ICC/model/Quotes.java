package com.example.ICC.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Quotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double quote;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stock_id_fk")
    private Stock stock;


    public Quotes(Double quote, Stock stock) {
        this.quote = quote;
        this.stock = stock;
    }

    public Quotes() {
    }

    public Long getId() {
        return id;
    }

    public Double getQuote() {
        return quote;
    }

    public Stock getStock() {
        return stock;
    }
}