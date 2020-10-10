package com.capg.fms.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.capg.fms.booking.model.Booking;
import com.capg.fms.booking.model.BookingList;
import com.capg.fms.booking.model.Flight;
import com.capg.fms.booking.model.Passenger;
import com.capg.fms.booking.model.PassengerList;
import com.capg.fms.booking.repository.IBookingRepo;
import com.capg.fms.exceptions.BookingIdExistsException;
import com.capg.fms.exceptions.BookingIdNotFoundException;
import com.capg.fms.exceptions.InvalidInputException;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService {

	@Autowired
	IBookingRepo repo;

	/************************** Add booking *********************************/
	@Override

	public Booking addBooking(Booking booking) {
		// Boolean book=repo.getOne(booking.getBookingId());
		if (validateBookingId(booking.getBookingId())) {
			if (validateNoOfPAssengers(booking.getNoOfPassengers())) {
				if (repo.existsById(booking.getBookingId())) {
					throw new BookingIdExistsException("Booking id is already present");
				}
			}
		}
		return repo.save(booking);
	}

	/************************** view byid ***************************************/
	@Override
	public Booking viewBookingByBookingId(long bookingId) {

		if (!repo.existsById(bookingId)) {
			throw new BookingIdNotFoundException("Booking id is not found");
		}
		return repo.getOne(bookingId);

	}

	/************************* viewall ******************************************/
	@Override
	public List<Booking> viewAllBookings() {
		List<Booking> bookings=repo.viewAllBookings();
		return bookings;
	}

	/******************************** delete *********************************/
	@Override
	public boolean deleteBooking(long bookingId) {

		if (!repo.existsById(bookingId)) {
			throw new BookingIdNotFoundException("Booking id is not found");
		}
		repo.deleteById(bookingId);

		return !repo.existsById(bookingId);
	}

	/**************************** modify ***************************************/
	@Override
	public Booking modifyBooking(Booking newBooking) {
		
		if (!repo.existsById(newBooking.getBookingId())) {
			throw new BookingIdNotFoundException("Booking id is not found");
		}
		Booking booking = repo.getOne(newBooking.getBookingId());
		booking.setUserId(newBooking.getUserId());
		booking.setBookingDate(newBooking.getBookingDate());
		booking.setPassengerList(newBooking.getPassengerList());
		booking.setTicketCost(newBooking.getTicketCost());
		booking.setFlightNumber(newBooking.getFlightNumber());
		booking.setNoOfPassengers(newBooking.getNoOfPassengers());
		return booking;
	}

	/************************ validatebooking *******************************/
	@Override
	public boolean validateBookingId(long bookingId) {
		int count = 0;
		long p = bookingId;
		long d;
		while (bookingId > 0) {
			d = bookingId % 10;
			count++;
			bookingId = bookingId / 10;
		}
		if (count != 3)
			throw new BookingIdNotFoundException("ID should be of 3 digits");

		
		return true;
	}

	/**************************** noofpassengers **********************************/
	@Override
	public boolean validateNoOfPAssengers(int noOfPassengers) {
		int availableSeats = 100;
		if (noOfPassengers == 0) {
			throw new InvalidInputException("Atleast 1 passenger is required to book a flight");
		} else if (noOfPassengers > availableSeats) {
			throw new InvalidInputException("The Capacity of flight is limited: i.e 100(hundred seats only available)");
		}
		return true;
	}

}
