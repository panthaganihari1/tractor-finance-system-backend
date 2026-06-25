package com.finanace.tractor_finance_system_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanace.tractor_finance_system_backend.dto.FarmerResponse;
import com.finanace.tractor_finance_system_backend.model.Booking;
import com.finanace.tractor_finance_system_backend.model.Farmer;
import com.finanace.tractor_finance_system_backend.repository.BookingRepository;
import com.finanace.tractor_finance_system_backend.repository.FarmerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FarmerService {

	private final FarmerRepository farmerRepository;
	private final BookingRepository bookingRepository;

	public List<FarmerResponse> getAllFarmers() {
		List<Booking> bookings = bookingRepository.findAll();
		return farmerRepository.findAll().stream()
			.map(farmer -> toResponse(farmer, bookings))
			.toList();
	}

	@Transactional
	public FarmerResponse createFarmer(Farmer farmer) {
		Farmer saved = farmerRepository.save(farmer);
		return toResponse(saved, bookingRepository.findAll());
	}

	@Transactional
	public void deleteFarmer(Long id) {
		farmerRepository.deleteById(id);
	}

	private FarmerResponse toResponse(Farmer farmer, List<Booking> bookings) {
		List<Booking> farmerBookings = bookings.stream()
			.filter(b -> farmer.getName().equals(b.getFarmer()))
			.toList();

		double totalAcres = farmerBookings.stream()
			.mapToDouble(Booking::getAcres)
			.sum();

		int totalPaid = farmerBookings.stream()
			.filter(b -> Boolean.TRUE.equals(b.getPaid()))
			.mapToInt(Booking::getTotalAmount)
			.sum();

		int totalDue = farmerBookings.stream()
			.filter(b -> !Boolean.TRUE.equals(b.getPaid()))
			.mapToInt(Booking::getTotalAmount)
			.sum();

		return FarmerResponse.builder()
			.id(farmer.getId())
			.name(farmer.getName())
			.phone(farmer.getPhone())
			.village(farmer.getVillage())
			.totalAcres(totalAcres)
			.totalDue(totalDue)
			.totalPaid(totalPaid)
			.build();
	}
}
