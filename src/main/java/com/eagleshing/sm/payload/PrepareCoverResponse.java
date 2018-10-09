package com.eagleshing.sm.payload;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.eagleshing.sm.model.Tag;

public class PrepareCoverResponse {

	private int id;

	private String title;
	
	private String img;

	private long latitude;

	private long longitude;

	private byte status;

	private String des;

	private int viewC;

	private int transmitC;

	private String address;

	private String project;

	private String projectDistrict;

	private int price;

	private String district;

	private Set<Tag> tags = new HashSet<>();
	
	private String author;
	
	private List<SaveDevisionResponse> devisions;

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

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
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
	
	
}
