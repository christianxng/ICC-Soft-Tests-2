package com.example.ICC.service;

import com.example.ICC.model.Quotes;
import com.example.ICC.model.Stock;
import com.example.ICC.model.request.QuotesRequest;
import com.example.ICC.model.request.StockRequest;
import com.example.ICC.model.response.StockResponse;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Stock create (StockRequest stockRequest){
        Stock stock = stockRequest.toStock();
        manager.persist(stock);
        stock.quotesAssociate(stockRequest.getQuotes().stream().map(quote -> new Quotes(quote,stock)).collect(Collectors.toList()));
        return stock;
    }

    public Stock findByName(String name){
        Query query = manager.createQuery("select s from Stock s where s.name = :name ");
        query.setParameter("name",name);
        return (Stock) query.getSingleResult();
    }

    @Transactional
    public Stock update(String name, QuotesRequest quotesRequest){
        Stock stock = this.findByName(name);
        stock.quotesAssociate(quotesRequest.getQuotes().stream().map(quote -> new Quotes(quote,stock)).collect(Collectors.toList()));
        return stock;
    }

    @Transactional
    public List<StockResponse> findAll(){
        List<Stock> stockList = (List<Stock>) manager.createQuery("select t from Stock t").getResultList();
        return stockList.stream().map(StockResponse::new).collect(Collectors.toList());
    }


    @Transactional
    public void deleteByName(String name){//
        Stock teste = this.findByName(name);
        manager.remove(teste);
    }
}
