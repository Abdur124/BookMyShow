package com.lld.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ShowSeat extends BaseModel {

	private int price;
	@ManyToOne
	private Show show;
	@ManyToOne
	private Seat seat;
	@Enumerated(EnumType.STRING)
	private ShowSeatstatus status;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public ShowSeatstatus getStatus() {
		return status;
	}

	public void setStatus(ShowSeatstatus status) {
		this.status = status;
	}

}
