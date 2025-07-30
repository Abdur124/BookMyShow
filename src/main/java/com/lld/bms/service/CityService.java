package com.lld.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.bms.exceptions.CityNotFoundException;
import com.lld.bms.models.City;
import com.lld.bms.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	public City saveCity(City city) {
		return cityRepository.save(city);
	}
	
	public List<City> getCities() {
		return cityRepository.findAll();
	}
	
	public City getCityById(int cityId) {
		
		return cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException("City with id " +cityId+ " not found"));
	}
	
	public void deleteCityById(int cityId) {
		cityRepository.deleteById(cityId);
	}

}
