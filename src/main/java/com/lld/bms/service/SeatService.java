package com.lld.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.bms.exceptions.SeatNotFoundException;
import com.lld.bms.models.Seat;
import com.lld.bms.repository.SeatRepository;

@Service
public class SeatService {
	
	@Autowired
	private SeatRepository seatRepository;
	
	public Seat saveSeat(Seat Seat) {
		return seatRepository.save(Seat);
	}
	
	public List<Seat> getSeats() {
		return seatRepository.findAll();
	}
	
	public Seat getSeatById(int SeatId) {
		
		return seatRepository.findById(SeatId).orElseThrow(() -> new SeatNotFoundException("Seat with id " +SeatId+ " not found"));
	}
	
	public void deleteSeatById(int SeatId) {
		seatRepository.deleteById(SeatId);
	}

}
