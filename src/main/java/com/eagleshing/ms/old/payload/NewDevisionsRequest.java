package com.eagleshing.ms.old.payload;

import java.util.ArrayList;
import java.util.List;

import com.eagleshing.ms.model.DevisionType;

public class NewDevisionsRequest {

	private int id;

	private String name;

	private String des;
	
	private byte sort;
	
	private DevisionType type;

	private String norm;
	
	private int mark;

	private int coverId;
	
	private int devSetId;
	
	private List<NewModuleRequest>	modules= new ArrayList<>();
	
	private List<NewParamsRequest> devisionParams = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<NewModuleRequest> getModules() {
		return modules;
	}

	public void setModules(List<NewModuleRequest> modules) {
		this.modules = modules;
	}

	public List<NewParamsRequest> getDevisionParams() {
		return devisionParams;
	}

	public void setDevisionParams(List<NewParamsRequest> devisionParams) {
		this.devisionParams = devisionParams;
	}

	public int getDevSetId() {
		return devSetId;
	}

	public void setDevSetId(int devSetId) {
		this.devSetId = devSetId;
	}
	
	
}
