package com.eagleshing.ms.old.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addition_info")
public class EagleDictionary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(length = 20)
	String key;
	
	@Column(length = 20)
	String value;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "type_id",nullable = false)
//	AdditionType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

//	public AdditionType getType() {
//		return type;
//	}
//
//	public void setType(AdditionType type) {
//		this.type = type;
//	}
//	
	

}
