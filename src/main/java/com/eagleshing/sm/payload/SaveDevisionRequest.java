package com.eagleshing.sm.payload;

import java.util.List;

import com.eagleshing.sm.model.Devision;

public class SaveDevisionRequest {
	
	private int coverId;
	
	private List<Devision> devisions;

	public int getCoverId() {
		return coverId;
	}

	public void setCoverId(int coverId) {
		this.coverId = coverId;
	}

	public List<Devision> getDevisions() {
		return devisions;
	}

	public void setDevisions(List<Devision> devisions) {
		this.devisions = devisions;
	}
	
	
}
