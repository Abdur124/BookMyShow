package com.lld.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.bms.exceptions.AuditoriumNotFoundException;
import com.lld.bms.models.Auditorium;
import com.lld.bms.repository.AuditoriumRepository;

@Service
public class AuditoriumService {
	
	@Autowired
	private AuditoriumRepository auditoriumRepository;
	
	public Auditorium saveAuditorium(Auditorium Auditorium) {
		return auditoriumRepository.save(Auditorium);
	}
	
	public List<Auditorium> getAuditoriums() {
		return auditoriumRepository.findAll();
	}
	
	public Auditorium getAuditoriumById(int AuditoriumId) {
		
		return auditoriumRepository.findById(AuditoriumId).orElseThrow(() -> new AuditoriumNotFoundException("Auditorium with id " +AuditoriumId+ " not found"));
	}
	
	public void deleteAuditoriumById(int AuditoriumId) {
		auditoriumRepository.deleteById(AuditoriumId);
	}

}
