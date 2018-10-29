package com.eagleshing.ms.model;

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

	private int mark;

	@Column(name="cover_id")
	private int coverId;

	@Column(name = "devset_id")
	private int devSetId;

	public Devision(DevisionSet devisionSet,Cover cover) {
		this.coverId = cover.getId();
		this.name = devisionSet.getName();
		this.devSetId = devisionSet.getId();
	}
	
	public Devision() {
		
	}
	
	public int getDevSetId() {
		return devSetId;
	}

	public void setDevSetId(int devSetId) {
		this.devSetId = devSetId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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



}
