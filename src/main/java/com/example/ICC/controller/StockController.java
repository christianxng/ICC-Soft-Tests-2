package com.example.ICC.controller;

import com.example.ICC.model.Stock;
import com.example.ICC.model.request.QuotesRequest;
import com.example.ICC.model.request.StockRequest;
import com.example.ICC.model.response.StockResponse;
import com.example.ICC.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/stock")
    public ResponseEntity<?>create(@RequestBody @Valid StockRequest stockRequest){
        Stock stock = stockService.create(stockRequest);
        URI uri = UriComponentsBuilder.fromUriString("/stock?name={name}").buildAndExpand((stock.getName())).toUri();
        return ResponseEntity.created(uri).body(new StockResponse(stock));
    }

    @PatchMapping("/stock/{name}")
    public ResponseEntity<?>update(@PathVariable @Valid String name, @RequestBody @Valid QuotesRequest quotesRequest){
        Stock stock = stockService.update(name,quotesRequest);
        return ResponseEntity.ok(new StockResponse(stock));
    }

    @GetMapping("/stock")
    public ResponseEntity<List<StockResponse>>findAll(){
        return ResponseEntity.ok(stockService.findAll());
    }
    @GetMapping("/stock/{name}")
    public ResponseEntity<StockResponse>findByName(@PathVariable @Valid String name){
        return ResponseEntity.ok(new StockResponse(stockService.findByName(name)));
    }

    @DeleteMapping("/stock/{name}")
    public ResponseEntity<?>delete(@PathVariable @Valid String name){
        stockService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}
