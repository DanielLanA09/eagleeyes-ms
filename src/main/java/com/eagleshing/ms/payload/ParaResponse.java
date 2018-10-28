package com.eagleshing.ms.payload;

import com.eagleshing.ms.model.DevisionParams;

public class ParaResponse {

    private int id;

    private String type;

    private String name;

    private String data;

    private String des;

    private boolean must;

    private byte sort;

    private int devisionId;

    public ParaResponse(){

    }

    public ParaResponse(DevisionParams param){
        this.type = param.getType();
        this.name = param.getName();
        this.data = param.getData();
        this.des = param.getDes();
        this.must = param.isMust();
        this.sort = param.getSort();
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
}
