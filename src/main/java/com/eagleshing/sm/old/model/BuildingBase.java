package com.eagleshing.sm.old.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BuildingBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(max=40)
	private String city;
	
	@Size(max=40)
	private String project ;
	
	@Column(name="area_name")
	private String areaName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cover_id")
	@JsonIgnore
	private OlderCover cover;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "base")
	private List<BuildingPrice> priceList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public OlderCover getCover() {
		return cover;
	}

	public void setCover(OlderCover cover) {
		this.cover = cover;
	}

	public List<BuildingPrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<BuildingPrice> priceList) {
		this.priceList = priceList;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}	
	
}
