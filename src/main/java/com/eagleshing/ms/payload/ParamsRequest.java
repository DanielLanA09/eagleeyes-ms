package com.eagleshing.ms.payload;

import java.util.ArrayList;
import java.util.List;

public class ParamsRequest {
	
	private int devisionId;
	
	private List<ParamResponse> params = new ArrayList<>();

	public int getDevisionId() {
		return devisionId;
	}

	public void setDevisionId(int devisionId) {
		this.devisionId = devisionId;
	}

	public List<ParamResponse> getParams() {
		return params;
	}

	public void setParams(List<ParamResponse> params) {
		this.params = params;
	}

	
	
}
