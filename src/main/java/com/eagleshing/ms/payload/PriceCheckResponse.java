package com.eagleshing.ms.payload;

import com.eagleshing.ms.scrapy.model.BuildingBase;
import com.eagleshing.ms.scrapy.model.BuildingPrice;

public class PriceCheckResponse {
	
	private String project;
	
	private String area;
	
	private int price;
	
	private int buildingBaseId;
	
	public PriceCheckResponse() {
		
	}
	
	public PriceCheckResponse(BuildingBase building,BuildingPrice price) {
		setProject(building.getProject());
		setArea(building.getAreaName());
		setPrice(price.getPrice());
		setBuildingBaseId(building.getId());
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBuildingBaseId() {
		return buildingBaseId;
	}

	public void setBuildingBaseId(int buildingBaseId) {
		this.buildingBaseId = buildingBaseId;
	}
	
	
}
