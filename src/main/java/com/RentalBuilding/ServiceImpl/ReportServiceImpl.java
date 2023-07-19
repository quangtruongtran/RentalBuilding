package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RentalBuilding.Converter.ReportConverter;
import com.RentalBuilding.DTO.MyUser;
import com.RentalBuilding.DTO.ReportDTO;
import com.RentalBuilding.Entity.ReportEntity;
import com.RentalBuilding.Repository.ReportRepository;
import com.RentalBuilding.Repository.UserRepository;
import com.RentalBuilding.Service.ReportService;


@Service
@Transactional
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReportRepository reportRepository;

	@Override
	public ReportDTO save(ReportDTO reportDTO) {
		MyUser userLogin = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (reportDTO.getId() == null) {
			ReportEntity report = ReportConverter.DTOtoEntity(reportDTO);
			report.setUser(userRepository.findByUserCode(userLogin.getUserCode()));
			return ReportConverter.EntitytoDTO(reportRepository.save(report));
		} else if (reportDTO.getId() != null) {
			Optional<ReportEntity> existedReport = reportRepository.findById(reportDTO.getId());
			existedReport.get().setReportContent(reportDTO.getReportContent());
			return ReportConverter.EntitytoDTO(reportRepository.save(existedReport.get()));
		}
		return null;
	}

	@Override
	public void deleteReport(Long id) {
		reportRepository.deleteById(id);
	}

	@Override
	public ReportDTO findById(Long id) {
		Optional<ReportEntity> report = reportRepository.findById(id);
		if(!report.isPresent()) {
			return null;
		}
		return ReportConverter.EntitytoDTO(report.get());
	}

	@Override
	public List<ReportDTO> getListReportsWithPaging(Pageable pageable) {
		List<ReportDTO> results = new ArrayList<>();
		for (ReportEntity report : reportRepository.findAll(pageable).getContent()) {
			results.add(ReportConverter.EntitytoDTO(report));
		}
		return results;
	}
	
}
