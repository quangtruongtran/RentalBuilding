package com.RentalBuilding.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FloorDTO {
	private Long id;
	private String floorName;
	private Integer floorArea;
	private String floorStatusName;
	private String floorTypeName;
}
