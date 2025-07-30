package com.lld.bms.models;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Auditorium extends BaseModel {

	private String name;
	@OneToMany
	@JoinColumn(name = "auditorium_id")
	private List<Seat> seats;
	@OneToMany
	@JoinColumn(name = "auditorium_id")
	private List<Show> shows;
	private int capacity;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<AudiFeature> features;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<AudiFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<AudiFeature> features) {
		this.features = features;
	}

}
