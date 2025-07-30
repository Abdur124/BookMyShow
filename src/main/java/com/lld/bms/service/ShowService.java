package com.lld.bms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.bms.exceptions.ShowNotFoundException;
import com.lld.bms.models.Seat;
import com.lld.bms.models.Show;
import com.lld.bms.models.ShowSeat;
import com.lld.bms.models.ShowSeatstatus;
import com.lld.bms.repository.ShowRepository;

@Service
public class ShowService {
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private ShowSeatService showSeatService;
	
	public Show saveShow(Show Show) {
		
		List<Seat> seats = Show.getAuditorium().getSeats();
		List<ShowSeat> showSeats = new ArrayList<>();
		Show = showRepository.save(Show);
		
		for(Seat seat: seats) {
			ShowSeat showSeat = new ShowSeat();
			showSeat.setSeat(seat);
			showSeat.setPrice(190);
			showSeat.setShow(Show);
			showSeat.setStatus(ShowSeatstatus.AVAILABLE);
			showSeat = showSeatService.saveShowSeat(showSeat);
			showSeats.add(showSeat);
		}
		
		Show.setShowSeats(showSeats);
		return Show;
	}
	
	public List<Show> getShows() {
		return showRepository.findAll();
	}
	
	public Show getShowById(int ShowId) {
		
		return showRepository.findById(ShowId).orElseThrow(() -> new ShowNotFoundException("Show with id " +ShowId+ " not found"));
	}
	
	public void deleteShowById(int ShowId) {
		showRepository.deleteById(ShowId);
	}

}
