package com.RentalBuilding.DTO;

import java.util.Date;

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
public class ContractDTO {
	private Long id;
	private String contractCode;
	private Integer contractPeriod;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
	@Temporal(TemporalType.DATE)
	private Date contractDateStart;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
	@Temporal(TemporalType.DATE)
	private Date contractDateEnd;
	private Integer contractDeposit;
	private String contractTaxCode;
	private String contractContent;
	private Integer contractCompensation;
	private Long customerId;
	private String spaceCode;
	private Long userId;
	private String customerIdentifyNumber;
	private String customerName;
	private String userCode;
	private String userName;
}
