package com.RentalBuilding.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDTO {
	private int spacesQuantity;
	private int customersQuantity;
	private int contractsQuantity;
	private long revenueThisMonth;
	private int estateContractAboutExpire;
}
