package com.capg.fms.booking.controller;


import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capg.fms.booking.model.Booking;
import com.capg.fms.booking.model.BookingList;
import com.capg.fms.booking.service.IBookingService;

@CrossOrigin
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	IBookingService service;

/*****************************show all booking**********************************/
	@GetMapping("/all")
	public ResponseEntity< List<Booking>> getAllBooking(){
		List<Booking> bookings  =service.viewAllBookings();	
		  return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
	}
/*****************************Add booking**************************************/
	@PostMapping("/add")
	public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
		
	      booking=service.addBooking(booking); 
	      return new ResponseEntity<Booking>(booking,HttpStatus.OK);
				      
/*****************************Delete booking***********************************/	
	}
	@DeleteMapping("/delete/{bookingId}")
	public ResponseEntity<Boolean> deleteBooking(@PathVariable long bookingId) {
     Boolean book=service.deleteBooking(bookingId);
     
     return new ResponseEntity<Boolean>(HttpStatus.OK);
     
/*****************************show by booking id********************************/	
	}	  
	@GetMapping("/id/{bookingId}")
	public ResponseEntity<Booking> viewBooking(@PathVariable long bookingId) {
		Booking booking=service.viewBookingByBookingId(bookingId);
		
		
			return new ResponseEntity<Booking>(booking,HttpStatus.OK);
		
			
/*****************************modify*********************************************/		
	}
	@PutMapping("/modify")
	public Booking modifyBooking(@RequestBody Booking booking) {
		return service.modifyBooking(booking);
	} 
	
}
