package com.RentalBuilding.Utils;

import com.RentalBuilding.DTO.ContractDTO;
import com.RentalBuilding.DTO.CustomerDTO;
import com.RentalBuilding.DTO.FloorDTO;
import com.RentalBuilding.DTO.ReportDTO;
import com.RentalBuilding.DTO.SpaceDTO;
import com.RentalBuilding.DTO.UserDTO;

public class Validate {
	public static boolean FloorValidate(FloorDTO floor) {
		if (floor.getFloorName() == null || floor.getFloorStatusName() == null || floor.getFloorTypeName() == null) {
			return false;
		}
		return true;
	}

	public static boolean UserValidate(UserDTO user) {
		if (user.getUserName() == null || user.getUserPassword() == null || user.getUserCode() == null
				|| user.getRoleCode().size() == 0 || user.getDepartmentId() == null) {
			return false;
		}
		return true;
	}
	
	public static boolean SpaceValidate(SpaceDTO space) {
		if(space.getSpaceCode() == null || space.getFloorName() == null || space.getSpaceStatusName() == null || space.getSpaceTypeName() == null) {
			return false;
		}
		return true;
	}
	
	public static boolean CustomerValidate(CustomerDTO customer) {
		if(customer.getCustomerName() == null || customer.getCustomerIdentifyNumber() == null || customer.getUserIds().size()==0) {
			return false;
		}
		return true;
	}
	
	public static boolean ContractValidate(ContractDTO contract) {
		if(contract.getContractCode()==null || contract.getContractPeriod() == null || contract.getContractDateStart() == null ||
				contract.getCustomerId() ==null || contract.getSpaceCode() == null || contract.getUserId() == null ) {
			return false;
		}
		return true;
	}
	
	public static boolean ReportValidate(ReportDTO reportDTO) {
		if(reportDTO.getReportContent() == null) {
			return false;
		}
		return true;
	}
}
