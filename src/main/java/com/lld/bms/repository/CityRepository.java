package com.lld.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lld.bms.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
