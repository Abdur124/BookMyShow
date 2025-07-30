package com.lld.bms.models;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Movie extends BaseModel {

	private String name;
	private String language;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<MovieFeature> features;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<MovieFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<MovieFeature> features) {
		this.features = features;
	}

}
