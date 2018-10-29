package com.eagleshing.ms.payload;

import java.util.ArrayList;
import java.util.List;

import com.eagleshing.ms.model.Devision;
import com.eagleshing.ms.model.DevisionSet;
import com.eagleshing.ms.model.DevisionType;

public class DevisionResponse {

	private int id;

	private String name;

	private String des;

	private String footerDes;

	private byte sort;

	private DevisionType type;

	private int mark;

	private int coverId;
	
	private int devSetId;

	private List<ParamResponse> paramsList = new ArrayList<>();

	private List<ModuleResponse> moduleList = new ArrayList<>();

	public DevisionResponse() {

	}

	public DevisionResponse(DevisionSet devSet) {
		this.name = devSet.getName();
		this.des = devSet.getDes();
		this.footerDes = devSet.getFooterDes();
		this.sort = devSet.getSort();
		this.type = devSet.getType();
		this.devSetId = devSet.getId();
	}

	public DevisionResponse(DevisionSet devSet, Devision dev) {
		this.name = devSet.getName();
		this.des = devSet.getDes();
		this.footerDes = devSet.getFooterDes();
		this.sort = devSet.getSort();
		this.type = devSet.getType();
		this.devSetId = devSet.getId();
		if (dev != null) {
			this.id = dev.getId();
			this.mark = dev.getMark();
			this.coverId = dev.getCoverId();
		}
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

	public List<ParamResponse> getParamsList() {
		return paramsList;
	}

	public void setParamsList(List<ParamResponse> paramsList) {
		this.paramsList = paramsList;
	}

	public String getFooterDes() {
		return footerDes;
	}

	public void setFooterDes(String footerDes) {
		this.footerDes = footerDes;
	}

	public List<ModuleResponse> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<ModuleResponse> moduleList) {
		this.moduleList = moduleList;
	}
}
