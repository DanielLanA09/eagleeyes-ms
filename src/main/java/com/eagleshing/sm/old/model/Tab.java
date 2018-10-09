package com.eagleshing.sm.old.model;

import java.util.ArrayList;
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
@Table(name = "tabs")
public class Tab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private boolean visible;
	
	@Column(name="good_value")
	private int goodValue;
	
	@Column(name="view_count")
	private int viewCount;
	
	@ManyToOne()
	@JoinColumn(name = "cover_id", nullable = false)
	@JsonIgnore
	private OlderCover cover;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "tabs")
//	private List<SubTabs> subTabs = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "tab")
	private List<AdditionType> additions = new ArrayList<>();

	public Tab() {
		
	}
	
	public Tab(String title,boolean visible) {
		this.title = title;
		this.visible = visible;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

//	public List<SubTabs> getSubTabs() {
//		return subTabs;
//	}
//
//	public void setSubTabs(List<SubTabs> subTabs) {
//		this.subTabs = subTabs;
//	}

	public int getGoodValue() {
		return goodValue;
	}

	public void setGoodValue(int goodValue) {
		this.goodValue = goodValue;
	}

	public List<AdditionType> getAdditions() {
		return additions;
	}

	public void setAdditions(List<AdditionType> additions) {
		this.additions = additions;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public OlderCover getCover() {
		return cover;
	}

	public void setCover(OlderCover cover) {
		this.cover = cover;
	}
	
	
}
