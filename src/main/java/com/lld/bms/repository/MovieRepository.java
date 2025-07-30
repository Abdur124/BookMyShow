package com.lld.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
