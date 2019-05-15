package com.eagleshing.ms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.eagleshing.ms.model.audit.DateAudit;

@Entity
@Table(name = "cover")
public class Cover extends DateAudit{
	
	private static final long serialVersionUID = 6638618114713978797L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length=20)
	private String title;
	
	private String img;

	private double latitude;

	private double longitude;

	private byte status;

	private String des;

	@Column(name="view_c")
	private int viewC;

	@Column(name="transmit_c")
	private int transmitC;

	private String address;

	@Column(length = 20)
	private String project;
	
	@Column(length=20,name="project_district")
	private String projectDistrict;

	private int price;
	
	@Column(length=20)
	private String district;

	@Column(length=10)
	private String author;
	
	@Column(name="building_id")
	private int buildingId=0;

	@Lob
	@Column(name = "video_src")
	private String videoSrc;
	
	public Cover() {
		
	}


	public String getVideoSrc() {
		return this.videoSrc;
	}

	public void setVideoSrc(String videoSrc) {
		this.videoSrc = videoSrc;
	}
	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProjectDistrict() {
		return projectDistrict;
	}

	public void setProjectDistrict(String projectDistrict) {
		this.projectDistrict = projectDistrict;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
	
	
}
