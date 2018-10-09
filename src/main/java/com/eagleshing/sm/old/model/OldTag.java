package com.eagleshing.sm.old.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tags")
public class OldTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 100)
	@NaturalId
	private String name;
	
	@Column(name="tag_type")
	private byte tagType;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tags")
	@JsonIgnore
	private Set<OlderCover> covers = new HashSet<>();

	public OldTag() {

	}

	public OldTag(String name,byte tagType) {
		this.name = name;
		this.tagType = tagType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<OlderCover> getCovers() {
		return covers;
	}

	public void setCovers(Set<OlderCover> covers) {
		this.covers = covers;
	}

	public byte getTagType() {
		return tagType;
	}

	public void setTagType(byte tagType) {
		this.tagType = tagType;
	}
	
	

}
