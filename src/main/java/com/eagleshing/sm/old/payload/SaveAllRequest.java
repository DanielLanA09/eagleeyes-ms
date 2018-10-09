package com.eagleshing.sm.old.payload;

import java.util.ArrayList;
import java.util.List;

public class SaveAllRequest {
	
	List<NewCoverRequest> covers = new ArrayList<>();

	public List<NewCoverRequest> getCovers() {
		return covers;
	}

	public void setCovers(List<NewCoverRequest> covers) {
		this.covers = covers;
	}
	
	
}
