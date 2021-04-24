package com.example.ICC.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   	
	@Column(unique = true)
    private String name;
    @Column

	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Quotes> quotes = new ArrayList<>();
    
    public Stock( String name, List<Quotes> quotes) {
		this.name = name;
		this.quotes = quotes;
	}
	public Stock( String name) {
		this.name = name;
	}
	public Stock() {
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<Quotes> getQuotes() {
		return quotes;
	}

    public void quotesAssociate(List<Quotes> quotes){
    	this.quotes.addAll(quotes);
	}

}
