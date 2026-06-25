package com.finanace.tractor_finance_system_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinanceSummaryResponse {

	private Integer totalIncome;
	private Integer totalExpense;
	private Integer totalDue;
	private Double totalAcres;
	private Integer netProfit;
}
