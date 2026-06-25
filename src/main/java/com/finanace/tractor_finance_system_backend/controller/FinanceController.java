package com.finanace.tractor_finance_system_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finanace.tractor_finance_system_backend.dto.FinanceSummaryResponse;
import com.finanace.tractor_finance_system_backend.model.Booking;
import com.finanace.tractor_finance_system_backend.model.Expense;
import com.finanace.tractor_finance_system_backend.service.FinanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class FinanceController {

	private final FinanceService financeService;

	@GetMapping("/summary")
	public FinanceSummaryResponse getSummary() {
		return financeService.getSummary();
	}

	@GetMapping("/expenses")
	public List<Expense> getExpenses() {
		return financeService.getExpenses();
	}

	@PostMapping("/expenses")
	@ResponseStatus(HttpStatus.CREATED)
	public Expense addExpense(@RequestBody Expense expense) {
		return financeService.addExpense(expense);
	}

	@DeleteMapping("/expenses/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteExpense(@PathVariable Long id) {
		financeService.deleteExpense(id);
	}

	@GetMapping("/income")
	public List<Booking> getIncomeRecords() {
		return financeService.getIncomeRecords();
	}
}
