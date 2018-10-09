package com.eagleshing.sm.scrapy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="building_price")
public class BuildingPrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="building_id")
	private int buildingId;
	
	private int price;
	
	@Column(name="price_time")
	private String priceTime;

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

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getPriceTime() {
		return priceTime;
	}

	public void setPriceTime(String priceTime) {
		this.priceTime = priceTime;
	}
	
	
}
