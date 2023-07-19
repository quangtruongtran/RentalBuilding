package com.RentalBuilding.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	private Long id;
	private String customerName;
	private String customerIdentifyNumber;
	private String customerEmail;
	private String customerPhone;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
	@Temporal(TemporalType.DATE)
	private Date customerDateOfBirth;
	private String customerAddress;
	private String customerStatus;
	private Set<Long> userIds = new HashSet<>();
	private List<String> listContractsCode = new ArrayList<>();
	private Map<String, String> users = new HashMap<>();
}
