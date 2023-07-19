package com.RentalBuilding.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String userName;
	private String userPassword;
	private String userCode;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
	@Temporal(TemporalType.DATE)
	private Date userDateOfBirth;
	private String userGender;
	private String userAddress;
	private String userPhone;
	private String userEmail;
	private String userImage;
	private List<String> roleCode = new ArrayList<>();
	private Long departmentId;
	private String departmentName;
}
