package com.RentalBuilding.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
	private String error;
	private Object data;
	public ResponseDTO(String error) {
		this.error = error;
	}
	
}
