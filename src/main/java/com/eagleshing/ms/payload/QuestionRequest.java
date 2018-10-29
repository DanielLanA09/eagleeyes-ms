package com.eagleshing.ms.payload;

import java.util.Set;

import com.eagleshing.ms.model.QuestionOption;

public class QuestionRequest {
	
	private int id;

	private String title;
	
	private boolean multiple;
	
	private byte max;
	
	Set<QuestionOption> options;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public byte getMax() {
		return max;
	}

	public void setMax(byte max) {
		this.max = max;
	}

	public Set<QuestionOption> getOptions() {
		return options;
	}

	public void setOptions(Set<QuestionOption> options) {
		this.options = options;
	}
	
	
}
