package com.eagleshing.ms.old.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BuildingPrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int price;
	
	@Column(name="price_time")
	private String priceTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="building_id")
	@JsonIgnore
	private BuildingBase base;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPriceTime() {
		return priceTime;
	}

	public void setPriceTime(String priceTime) {
		this.priceTime = priceTime;
	}

	public BuildingBase getBase() {
		return base;
	}

	public void setBase(BuildingBase base) {
		this.base = base;
	}
	
	
}
