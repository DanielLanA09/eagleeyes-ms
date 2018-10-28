package com.eagleshing.ms.old.payload;

import com.eagleshing.ms.model.ModuleBranch;

public class NewModuleRequest {
	
	private int id;

	private String name;

	private ModuleBranch branch;

	private byte sort;

	private byte status;

	private String des;

	private String jsonContent;

	private int devisionId;

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

	public ModuleBranch getBranch() {
		return branch;
	}

	public void setBranch(ModuleBranch branch) {
		this.branch = branch;
	}

	public byte getSort() {
		return sort;
	}

	public void setSort(byte sort) {
		this.sort = sort;
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

	public String getJsonContent() {
		return jsonContent;
	}

	public void setJsonContent(String jsonContent) {
		this.jsonContent = jsonContent;
	}

	public int getDevisionId() {
		return devisionId;
	}

	public void setDevisionId(int devisionId) {
		this.devisionId = devisionId;
	}
	
	

}