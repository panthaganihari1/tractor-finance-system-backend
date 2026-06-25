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
public class FarmerResponse {

	private Long id;
	private String name;
	private String phone;
	private String village;
	private Double totalAcres;
	private Integer totalDue;
	private Integer totalPaid;
}
