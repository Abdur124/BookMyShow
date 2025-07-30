package com.lld.bms.dto;

import java.util.List;

public class TicketReqDTO {

	private List<Integer> showSeatIDs;
	private int userId;

	public List<Integer> getShowSeatIDs() {
		return showSeatIDs;
	}

	public void setShowSeatIDs(List<Integer> showSeatIDs) {
		this.showSeatIDs = showSeatIDs;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
