package com.RentalBuilding.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
	private Long id;
	private String reportTitle;
	private String reportContent;
	private Long userId;
	private String userCode;
	private String userName;
}
