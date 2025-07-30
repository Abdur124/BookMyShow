package com.lld.bms.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.bms.models.Auditorium;
import com.lld.bms.models.City;
import com.lld.bms.models.Movie;
import com.lld.bms.models.Seat;
import com.lld.bms.models.SeatType;
import com.lld.bms.models.Seatstatus;
import com.lld.bms.models.Show;
import com.lld.bms.models.ShowStatus;
import com.lld.bms.models.Theatre;

@Service
public class InitService {
	
	@Autowired
    private CityService cityService;
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ShowService showService;
	
	
	public void initialise() {
	
	List<Seat> seats = new ArrayList<>();
	
	for(int i=1; i<=100; i++) {
		Seat seat = new Seat();
		seat.setRow(i);
		seat.setCol(i);
		seat.setSeatNumber("Seat - " +i);
		seat.setSeatType(SeatType.GOLD);
		seat.setSeatStatus(Seatstatus.AVAILABLE);
		seat = seatService.saveSeat(seat);
		seats.add(seat);
	}
	
	Auditorium audi = new Auditorium();
	
	audi.setSeats(seats);
	audi.setCapacity(100);
	audi.setName("AUDI 01");
	
	audi = auditoriumService.saveAuditorium(audi);
	
	Theatre theatre = new Theatre();
	theatre.setName("PVR Grand Gallada");
	theatre.setAuditoriums(List.of(audi));
	theatre.setAddress("Pallavaram, Chennai");
	
	theatre = theatreService.saveTheatre(theatre);
	
	City city = new City();
	city.setName("Chennai");
	city.setTheatres(List.of(theatre));
	
	city = cityService.saveCity(city);
	
	Movie movie = new Movie();
	movie.setName("Sitaare Zameen Par");
	movie.setLanguage("Hindi");
	
	movie = movieService.saveMovie(movie);
	
	Show show = new Show();
	show.setAuditorium(audi);
	show.setMovie(movie);
	show.setShowStatus(ShowStatus.YET_TO_START);
	show.setStartTime(LocalDateTime.now());
	show.setEndTime(LocalDateTime.now());
	
	show = showService.saveShow(show);
	
	}
	
}
