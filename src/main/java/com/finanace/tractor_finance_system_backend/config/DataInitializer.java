package com.finanace.tractor_finance_system_backend.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.finanace.tractor_finance_system_backend.model.Booking;
import com.finanace.tractor_finance_system_backend.model.Expense;
import com.finanace.tractor_finance_system_backend.model.Farmer;
import com.finanace.tractor_finance_system_backend.repository.BookingRepository;
import com.finanace.tractor_finance_system_backend.repository.ExpenseRepository;
import com.finanace.tractor_finance_system_backend.repository.FarmerRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	private final FarmerRepository farmerRepository;
	private final BookingRepository bookingRepository;
	private final ExpenseRepository expenseRepository;

	@Override
	public void run(String... args) {
		if (farmerRepository.count() > 0) {
			return;
		}

		farmerRepository.saveAll(List.of(
			Farmer.builder().name("రామయ్య").phone("9876543210").village("నల్లగొండ").build(),
			Farmer.builder().name("వెంకటేశ్వర రావు").phone("9876543211").village("మిర్యాలగూడ").build(),
			Farmer.builder().name("లక్ష్మయ్య").phone("9876543212").village("సూర్యాపేట").build(),
			Farmer.builder().name("నరసింహారావు").phone("9876543213").village("హాలియా").build(),
			Farmer.builder().name("సుబ్బారావు").phone("9876543214").village("తిరుమలగిరి").build()
		));

		bookingRepository.saveAll(List.of(
			Booking.builder().farmer("రామయ్య").village("నల్లగొండ").nagaliType("గొర్రు").acres(3.0).ratePerAcre(800)
				.date(LocalDate.of(2025, 1, 15)).paid(true).totalAmount(2400).build(),
			Booking.builder().farmer("వెంకటేశ్వర రావు").village("మిర్యాలగూడ").nagaliType("మిరప గొర్రు").acres(2.0).ratePerAcre(1000)
				.date(LocalDate.of(2025, 1, 16)).paid(false).totalAmount(2000).build(),
			Booking.builder().farmer("లక్ష్మయ్య").village("సూర్యాపేట").nagaliType("వితనం గొర్రు").acres(5.0).ratePerAcre(900)
				.date(LocalDate.of(2025, 1, 17)).paid(true).totalAmount(4500).build(),
			Booking.builder().farmer("నరసింహారావు").village("హాలియా").nagaliType("బోతె గొర్రు").acres(4.0).ratePerAcre(850)
				.date(LocalDate.of(2025, 1, 18)).paid(false).totalAmount(3400).build(),
			Booking.builder().farmer("సుబ్బారావు").village("తిరుమలగిరి").nagaliType("గొర్రు").acres(2.5).ratePerAcre(800)
				.date(LocalDate.of(2025, 1, 19)).paid(true).totalAmount(2000).build()
		));

		expenseRepository.saveAll(List.of(
			Expense.builder().type("డీజిల్").amount(3500).date(LocalDate.of(2025, 1, 15)).note("50 లీటర్లు @ ₹70").build(),
			Expense.builder().type("Maintenance").amount(2000).date(LocalDate.of(2025, 1, 16)).note("Oil change + filter").build(),
			Expense.builder().type("Driver జీతం").amount(12000).date(LocalDate.of(2025, 1, 1)).note("January జీతం").build(),
			Expense.builder().type("డీజిల్").amount(2800).date(LocalDate.of(2025, 1, 18)).note("40 లీటర్లు @ ₹70").build(),
			Expense.builder().type("Spare parts").amount(1500).date(LocalDate.of(2025, 1, 20)).note("Tyre repair").build()
		));
	}
}
