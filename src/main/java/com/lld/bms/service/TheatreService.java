package com.lld.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.bms.exceptions.TheatreNotFoundException;
import com.lld.bms.models.Theatre;
import com.lld.bms.repository.TheatreRepository;

@Service
public class TheatreService {
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	public Theatre saveTheatre(Theatre Theatre) {
		return theatreRepository.save(Theatre);
	}
	
	public List<Theatre> getTheatres() {
		return theatreRepository.findAll();
	}
	
	public Theatre getTheatreById(int TheatreId) {
		
		return theatreRepository.findById(TheatreId).orElseThrow(() -> new TheatreNotFoundException("Theatre with id " +TheatreId+ " not found"));
	}
	
	public void deleteTheatreById(int TheatreId) {
		theatreRepository.deleteById(TheatreId);
	}

}
