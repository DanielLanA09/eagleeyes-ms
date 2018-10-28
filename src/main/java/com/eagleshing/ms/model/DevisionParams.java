package com.eagleshing.ms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="devision_params")
public class DevisionParams {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 10)
	private String type;
	
	@Column(length = 20)
	private String name;
	
	@Column(length = 20)
	private String data;
	
	@Column(length=100)
	private String des;
	
	private boolean must;
	
	private byte sort;

	@Column(name="devision_id")
	private int devisionId;

	public DevisionParams(DevisionParamsSet paramsSet,Devision devision) {
		this.type = paramsSet.getType();
		this.name = paramsSet.getName();
		this.must = paramsSet.isMust();
		this.des = paramsSet.getDes();
		this.sort = paramsSet.getSort();
		this.devisionId = devision.getCoverId();
	}
	
	public DevisionParams() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isMust() {
		return must;
	}

	public void setMust(boolean must) {
		this.must = must;
	}

	public int getDevisionId() {
		return devisionId;
	}

	public void setDevisionId(int devisionId) {
		this.devisionId = devisionId;
	}

	public byte getSort() {
		return sort;
	}

	public void setSort(byte sort) {
		this.sort = sort;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	
}
