package com.RentalBuilding.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.RentalBuilding.DTO.ReportDTO;

public interface ReportService {
	ReportDTO save(ReportDTO reportDTO);
	void deleteReport(Long id);
	ReportDTO findById(Long id);
	List<ReportDTO> getListReportsWithPaging(Pageable pageable);
}
