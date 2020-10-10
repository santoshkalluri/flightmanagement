package com.capg.fms.booking.service;

import java.util.List;

import com.capg.fms.booking.model.Booking;
import com.capg.fms.booking.model.BookingList;
import com.capg.fms.booking.model.Passenger;

public interface IBookingService{
	
	public Booking addBooking(Booking booking) ;
	public Booking viewBookingByBookingId(long bookingId);
	public List<Booking> viewAllBookings();
	public boolean deleteBooking(long bookingId);
	public Booking modifyBooking(Booking newBooking);
	
	boolean validateBookingId(long bookingId);
	boolean validateNoOfPAssengers(int noOfPassengers);

}
