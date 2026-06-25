package com.finanace.tractor_finance_system_backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanace.tractor_finance_system_backend.dto.FinanceSummaryResponse;
import com.finanace.tractor_finance_system_backend.model.Booking;
import com.finanace.tractor_finance_system_backend.model.Expense;
import com.finanace.tractor_finance_system_backend.repository.BookingRepository;
import com.finanace.tractor_finance_system_backend.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FinanceService {

	private static final Map<String, Integer> NAGALI_RATES = Map.of(
		"గొర్రు", 800,
		"మిరప గొర్రు", 1000,
		"వితనం గొర్రు", 900,
		"బోతె గొర్రు", 850
	);

	private final BookingRepository bookingRepository;
	private final ExpenseRepository expenseRepository;

	public FinanceSummaryResponse getSummary() {
		List<Booking> bookings = bookingRepository.findAll();
		List<Expense> expenses = expenseRepository.findAll();

		int totalIncome = bookings.stream().mapToInt(Booking::getTotalAmount).sum();
		int totalExpense = expenses.stream().mapToInt(Expense::getAmount).sum();
		int totalDue = bookings.stream()
			.filter(b -> !Boolean.TRUE.equals(b.getPaid()))
			.mapToInt(Booking::getTotalAmount)
			.sum();
		double totalAcres = bookings.stream().mapToDouble(Booking::getAcres).sum();

		return FinanceSummaryResponse.builder()
			.totalIncome(totalIncome)
			.totalExpense(totalExpense)
			.totalDue(totalDue)
			.totalAcres(totalAcres)
			.netProfit(totalIncome - totalExpense)
			.build();
	}

	public List<Expense> getExpenses() {
		return expenseRepository.findAll();
	}

	@Transactional
	public Expense addExpense(Expense expense) {
		return expenseRepository.save(expense);
	}

	@Transactional
	public void deleteExpense(Long id) {
		expenseRepository.deleteById(id);
	}

	public List<Booking> getIncomeRecords() {
		return bookingRepository.findAll();
	}

	public static int resolveRate(String nagaliType) {
		return NAGALI_RATES.getOrDefault(nagaliType, 800);
	}
}
