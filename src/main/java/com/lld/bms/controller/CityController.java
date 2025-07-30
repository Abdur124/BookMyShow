package com.lld.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lld.bms.models.City;
import com.lld.bms.service.CityService;

@RestController
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@PostMapping("/city")
	public ResponseEntity<City> createCity(@RequestBody City city) {
		
		City savedCity = cityService.saveCity(city);
		System.out.println("CityName: " +city);
		return ResponseEntity.ok(savedCity);
	}
	
	@GetMapping("/cities")
	public ResponseEntity<List<City>> getAllCities() {
		List<City> cities = cityService.getCities();
		return ResponseEntity.ok(cities);
	}
	
	@GetMapping("city/{id}")
	public ResponseEntity<City> getCityById(@PathVariable("id") int cityId) {
		City city = cityService.getCityById(cityId);
		return ResponseEntity.ok(city);
	}
	
	@DeleteMapping("city/{id}")
	public ResponseEntity<String> deleteCityById(@PathVariable("id") int cityId) {
		cityService.deleteCityById(cityId);
		return ResponseEntity.ok("City Deleted");
	}

}
