package com.eagleshing.sm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="devision")
public class Devision {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=24)
	private String name;

	@Column(length=255)
	private String des;
	
	@Column(length=255)
	private String footerDes;
	
	private byte sort;
	
	private DevisionType type;
	
	@Column(length=255)
	private String norm;
	
	private int mark;

	@Column(name="cover_id")
	private int coverId;

	@Column(name = "devset_id")
	private int devset_id;

	public Devision(DevisionSet devisionSet,Cover cover) {
		this.coverId = cover.getId();
		this.name = devisionSet.getName();
		this.des = devisionSet.getDes();
		this.footerDes = devisionSet.getFooterDes();
		this.sort = devisionSet.getSort();
		this.devset_id = devisionSet.getId();
	}
	
	public Devision() {
		
	}

	public String getFooterDes() {
		return footerDes;
	}

	public void setFooterDes(String footerDes) {
		this.footerDes = footerDes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public byte getSort() {
		return sort;
	}

	public void setSort(byte sort) {
		this.sort = sort;
	}

	public DevisionType getType() {
		return type;
	}

	public void setType(DevisionType type) {
		this.type = type;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public int getCoverId() {
		return coverId;
	}

	public void setCoverId(int coverId) {
		this.coverId = coverId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDevset_id() {
		return devset_id;
	}

	public void setDevset_id(int devset_id) {
		this.devset_id = devset_id;
	}
}
