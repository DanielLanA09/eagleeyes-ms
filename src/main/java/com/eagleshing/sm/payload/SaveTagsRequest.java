package com.eagleshing.sm.payload;

import java.util.List;

import com.eagleshing.sm.model.Tag;

public class SaveTagsRequest {
	
	public int coverid;
	
	public List<Tag> tags;

	public int getCoverid() {
		return coverid;
	}

	public void setCoverid(int coverid) {
		this.coverid = coverid;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
}
