package com.lld.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
