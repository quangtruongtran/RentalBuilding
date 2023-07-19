package com.RentalBuilding.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpaceDTO {
	private Long id;
	private String spaceCode;
	private Integer spaceArea;
	private Integer spacePrice;
	private Integer spaceServiceFee;
	private String spaceDescription;
	private String spaceImage;
	private String floorName;
	private String spaceStatusName;
	private String spaceTypeName;
	private String contractCode;
}
