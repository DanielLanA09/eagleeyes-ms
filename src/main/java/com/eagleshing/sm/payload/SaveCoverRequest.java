package com.eagleshing.sm.payload;

import java.util.HashSet;
import java.util.Set;

import com.eagleshing.sm.model.Cover;
import com.eagleshing.sm.model.Tag;

public class SaveCoverRequest {
	
	private Cover cover;
	
	private Set<Tag> tags = new HashSet<>();

	public Cover getCover() {
		return cover;
	}

	public void setCover(Cover cover) {
		this.cover = cover;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	
}
