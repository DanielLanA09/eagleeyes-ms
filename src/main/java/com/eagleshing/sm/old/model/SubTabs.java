package com.eagleshing.sm.old.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "sub_tabs")
public class SubTabs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private boolean comparison;

	private String advantage;

	private String disadvantage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tab_id", nullable = false)
	@JsonIgnore
	private Tab tabs;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "subTabs")
	private List<PostContent> posts = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public Tab getTabs() {
		return tabs;
	}

	public void setTabs(Tab tabs) {
		this.tabs = tabs;
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

	public boolean isComparison() {
		return comparison;
	}

	public void setComparison(boolean comparison) {
		this.comparison = comparison;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getDisadvantage() {
		return disadvantage;
	}

	public void setDisadvantage(String disadvantage) {
		this.disadvantage = disadvantage;
	}

	public List<PostContent> getPosts() {
		return posts;
	}

	public void setPosts(List<PostContent> posts) {
		this.posts = posts;
	}


}
