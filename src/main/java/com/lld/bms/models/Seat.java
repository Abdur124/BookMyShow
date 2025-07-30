package com.lld.bms.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Seat extends BaseModel {

	@Column(name = "seat_row")
	private int row;
	
	@Column(name = "seat_col")
	private int col;
	private String seatNumber;
	@Enumerated(EnumType.STRING)
	private SeatType seatType;
	@Enumerated(EnumType.STRING)
	private Seatstatus seatStatus;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public Seatstatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(Seatstatus seatStatus) {
		this.seatStatus = seatStatus;
	}

}
