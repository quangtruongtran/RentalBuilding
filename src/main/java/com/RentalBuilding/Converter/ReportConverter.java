package com.RentalBuilding.Converter;

import com.RentalBuilding.DTO.ReportDTO;
import com.RentalBuilding.Entity.ReportEntity;

public class ReportConverter extends Converter {
	
		public static ReportEntity DTOtoEntity(ReportDTO reportDTO) {
			ReportEntity obj = new ReportEntity();
			obj = modelMapper.map(reportDTO, ReportEntity.class);
			return obj;
		}
		
		public static ReportDTO EntitytoDTO(ReportEntity reportEntity) {
			ReportDTO obj = modelMapper.map(reportEntity, ReportDTO.class);
			obj.setUserCode(reportEntity.getUser().getUserCode());
			obj.setUserName(reportEntity.getUser().getUserName());
			obj.setUserId(reportEntity.getUser().getId());
			return obj;
		}

}
