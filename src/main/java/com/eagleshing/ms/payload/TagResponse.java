package com.eagleshing.ms.payload;

import com.eagleshing.ms.model.Tag;

public class TagResponse {

    private int id;

    private String name;

    private String type;

    public TagResponse(){

    }

    public TagResponse(Tag tag){
        this.id = tag.getId();
        this.name = tag.getName();
        this.type = tag.getType();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
