package com.capg.fms.booking.repository;

import java.util.List;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.fms.booking.model.Booking;
@Repository
@Persistent
public interface IBookingRepo extends JpaRepository<Booking, Long>{

	@Query("from Booking ")
	public List<Booking> viewAllBookings();	
}
