package com.eagleshing.sm.payload;

import java.util.ArrayList;
import java.util.List;

import com.eagleshing.sm.model.Devision;
import com.eagleshing.sm.model.DevisionParams;
import com.eagleshing.sm.model.DevisionSet;
import com.eagleshing.sm.model.DevisionType;
import com.eagleshing.sm.model.Module;

public class SaveDevisionResponse {

	private int id;

	private String name;

	private String des;

	private byte sort;

	private DevisionType type;

	private String norm;

	private int mark;

	private int coverId;

	private List<DevisionParams> paramsList = new ArrayList<>();

	private List<Module> moduleList = new ArrayList<>();

	public SaveDevisionResponse(Devision devision) {
		this.id = devision.getId();
		this.name = devision.getName();
		this.des = devision.getDes();
		this.sort = devision.getSort();
		this.type = devision.getType();
		this.norm = devision.getNorm();
		this.mark = devision.getMark();
		this.coverId = devision.getCoverId();
	}

	public SaveDevisionResponse(DevisionSet devision, int coverId) {
		this.id = devision.getId();
		this.name = devision.getName();
		this.des = devision.getDes();
		this.sort = devision.getSort();
		this.coverId = coverId;
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

	public List<DevisionParams> getParamsList() {
		return paramsList;
	}

	public void setParamsList(List<DevisionParams> paramsList) {
		this.paramsList = paramsList;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

}
