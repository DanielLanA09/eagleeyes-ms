package com.eagleshing.ms.old.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="addition_type")
public class AdditionType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=20,name="type_name")
	private String typeName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tab_id")
	@JsonIgnore
	private Tab tab;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="type")
	private List<AdditionContent> additionInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Tab getTab() {
		return tab;
	}

	public void setTab(Tab tab) {
		this.tab = tab;
	}

	public List<AdditionContent> getAdditionInfo() {
		return additionInfo;
	}

	public void setAdditionInfo(List<AdditionContent> additionInfo) {
		this.additionInfo = additionInfo;
	}




	
	

}
