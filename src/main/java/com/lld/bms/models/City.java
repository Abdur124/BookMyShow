package com.lld.bms.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
public class City extends BaseModel{
	
	private String name;
	@OneToMany
	@JoinColumn(name="city_id")
	private List<Theatre> theatres;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Theatre> getTheatres() {
		return theatres;
	}


	public void setTheatres(List<Theatre> theatres) {
		this.theatres = theatres;
	}


	@Override
	public String toString() {
		return "City [name=" + name + ", theatres=" + theatres + "]";
	}

	
}
