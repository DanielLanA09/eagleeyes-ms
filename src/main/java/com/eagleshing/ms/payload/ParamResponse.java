package com.eagleshing.ms.payload;

import com.eagleshing.ms.model.DevisionParams;
import com.eagleshing.ms.model.DevisionParamsSet;

public class ParamResponse {

    private int id;

    private String type;

    private String name;

    private String data;

    private String des;

    private boolean must;

    private byte sort;

    private int devisionId;
    
    private int paramSetId;

    public ParamResponse(){

    }

    public ParamResponse(DevisionParams param){
    	this.id = param.getId();
        this.type = param.getType();
        this.name = param.getName();
        this.data = param.getData();
        this.des = param.getDes();
        this.must = param.isMust();
        this.sort = param.getSort();
        this.devisionId = param.getDevisionId();
        this.paramSetId = param.getParamSetId();
    }
    
    public ParamResponse(DevisionParamsSet param,int devId) {
    	this.paramSetId = param.getId();
    	this.type = param.getType();
        this.name = param.getName();
        this.des = param.getDes();
        this.must = param.isMust();
        this.sort = param.getSort();
        this.devisionId = devId;
    }
    
    public ParamResponse(DevisionParamsSet param,int devId,int id) {
    	this.paramSetId = param.getId();
    	this.type = param.getType();
        this.name = param.getName();
        this.des = param.getDes();
        this.must = param.isMust();
        this.sort = param.getSort();
        this.devisionId = devId;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isMust() {
        return must;
    }

    public void setMust(boolean must) {
        this.must = must;
    }

    public byte getSort() {
        return sort;
    }

    public void setSort(byte sort) {
        this.sort = sort;
    }

    public int getDevisionId() {
        return devisionId;
    }

    public void setDevisionId(int devisionId) {
        this.devisionId = devisionId;
    }

	public int getParamSetId() {
		return paramSetId;
	}

	public void setParamSetId(int paramSetId) {
		this.paramSetId = paramSetId;
	}
    
    
}
