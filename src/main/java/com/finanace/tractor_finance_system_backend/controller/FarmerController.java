package com.finanace.tractor_finance_system_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finanace.tractor_finance_system_backend.dto.FarmerResponse;
import com.finanace.tractor_finance_system_backend.model.Booking;
import com.finanace.tractor_finance_system_backend.model.Farmer;
import com.finanace.tractor_finance_system_backend.service.BookingService;
import com.finanace.tractor_finance_system_backend.service.FarmerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FarmerController {

	private final FarmerService farmerService;
	private final BookingService bookingService;

	@GetMapping("/farmers")
	public List<FarmerResponse> getAllFarmers() {
		return farmerService.getAllFarmers();
	}

	@PostMapping("/farmers")
	@ResponseStatus(HttpStatus.CREATED)
	public FarmerResponse createFarmer(@RequestBody Farmer farmer) {
		return farmerService.createFarmer(farmer);
	}

	@DeleteMapping("/farmers/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFarmer(@PathVariable Long id) {
		farmerService.deleteFarmer(id);
	}

	@GetMapping("/bookings")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@PostMapping("/bookings")
	@ResponseStatus(HttpStatus.CREATED)
	public Booking createBooking(@RequestBody Booking booking) {
		return bookingService.createBooking(booking);
	}

	@PutMapping("/bookings/{id}/payment")
	public Booking updatePaymentStatus(@PathVariable Long id, @RequestParam boolean paid) {
		return bookingService.updatePaymentStatus(id, paid);
	}

	@DeleteMapping("/bookings/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);
	}
}
