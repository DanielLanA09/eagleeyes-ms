package com.eagleshing.ms.payload;

import java.util.HashSet;
import java.util.Set;

import com.eagleshing.ms.model.Cover;
import com.eagleshing.ms.model.Tag;

public class CoverRequest {
	
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
