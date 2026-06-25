package com.finanace.tractor_finance_system_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanace.tractor_finance_system_backend.model.Booking;
import com.finanace.tractor_finance_system_backend.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepository bookingRepository;

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Transactional
	public Booking createBooking(Booking booking) {
		if (booking.getRatePerAcre() == null) {
			booking.setRatePerAcre(FinanceService.resolveRate(booking.getNagaliType()));
		}
		if (booking.getTotalAmount() == null && booking.getAcres() != null) {
			booking.setTotalAmount((int) (booking.getAcres() * booking.getRatePerAcre()));
		}
		if (booking.getPaid() == null) {
			booking.setPaid(false);
		}
		return bookingRepository.save(booking);
	}

	@Transactional
	public Booking updatePaymentStatus(Long id, boolean paid) {
		Booking booking = bookingRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Booking not found: " + id));
		booking.setPaid(paid);
		return bookingRepository.save(booking);
	}

	@Transactional
	public void deleteBooking(Long id) {
		bookingRepository.deleteById(id);
	}
}
