package com.lld.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.bms.exceptions.ShowSeatNotFoundException;
import com.lld.bms.models.ShowSeat;
import com.lld.bms.repository.ShowSeatRepository;

@Service
public class ShowSeatService {
	
	@Autowired
	private ShowSeatRepository showSeatRepository;
	
	public ShowSeat saveShowSeat(ShowSeat ShowSeat) {
		return showSeatRepository.save(ShowSeat);
	}
	
	public ShowSeat updateShowSeat(ShowSeat ShowSeat) {
		return showSeatRepository.save(ShowSeat);
	}
	
	public List<ShowSeat> getShowSeats() {
		return showSeatRepository.findAll();
	}
	
	public ShowSeat getShowSeatById(int ShowSeatId) {
		
		return showSeatRepository.findById(ShowSeatId).orElseThrow(() -> new ShowSeatNotFoundException("ShowSeat with id " +ShowSeatId+ " not found"));
	}
	
	public void deleteShowSeatById(int ShowSeatId) {
		showSeatRepository.deleteById(ShowSeatId);
	}

}
