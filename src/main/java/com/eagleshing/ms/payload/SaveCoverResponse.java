package com.eagleshing.ms.payload;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.eagleshing.ms.model.Cover;
import com.eagleshing.ms.model.Tag;

public class SaveCoverResponse {
	
	private int id;

	private String title;
	
	private String img;

	private double latitude;

	private double longitude;

	private byte status;

	private String des;

	private int viewC;

	private int transmitC;

	private String address;

	private String project;
	
	private String projectDistrict;

	private int price;
	
	private String district;

	private String author;

	private Set<TagResponse> tags = new HashSet<>();

    private List<SaveDevisionResponse> devisions = new ArrayList<>();

    public SaveCoverResponse(){

	}

	public SaveCoverResponse(Cover cover){
    	this.id = cover.getId();
    	this.title = cover.getTitle();
    	this.img = cover.getImg();
    	this.latitude = cover.getLatitude();
    	this.longitude = cover.getLongitude();
    	this.status = cover.getStatus();
    	this.des = cover.getDes();
    	this.viewC = cover.getViewC();
    	this.transmitC = cover.getTransmitC();
    	this.address = cover.getAddress();
    	this.project = cover.getProject();
    	this.projectDistrict = cover.getProjectDistrict();
    	this.price = cover.getPrice();
    	this.district = cover.getDistrict();
    	this.author = cover.getAuthor();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getViewC() {
		return viewC;
	}

	public void setViewC(int viewC) {
		this.viewC = viewC;
	}

	public int getTransmitC() {
		return transmitC;
	}

	public void setTransmitC(int transmitC) {
		this.transmitC = transmitC;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getProjectDistrict() {
		return projectDistrict;
	}

	public void setProjectDistrict(String projectDistrict) {
		this.projectDistrict = projectDistrict;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<SaveDevisionResponse> getDevisions() {
		return devisions;
	}

	public void setDevisions(List<SaveDevisionResponse> devisions) {
		this.devisions = devisions;
	}

    public void setTags(Set<TagResponse> tags) {
        this.tags = tags;
    }

	public Set<TagResponse> getTags() {
		return tags;
	}
}
