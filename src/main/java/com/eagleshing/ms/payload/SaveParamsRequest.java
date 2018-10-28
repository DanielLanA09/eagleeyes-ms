package com.eagleshing.ms.payload;

import java.util.ArrayList;
import java.util.List;

import com.eagleshing.ms.model.DevisionParams;

public class SaveParamsRequest {
	private int devisionId;
	
	private List<DevisionParams> params = new ArrayList<>();

	public int getDevisionId() {
		return devisionId;
	}

	public void setDevisionId(int devisionId) {
		this.devisionId = devisionId;
	}

	public List<DevisionParams> getParams() {
		return params;
	}

	public void setParams(List<DevisionParams> params) {
		this.params = params;
	}
	
	
}
