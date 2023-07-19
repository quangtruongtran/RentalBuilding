package com.RentalBuilding.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RentalBuilding.DTO.ReportDTO;
import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.Exception.FieldRequiredException;
import com.RentalBuilding.Exception.NotExistedException;
import com.RentalBuilding.Service.ReportService;
import com.RentalBuilding.Utils.Validate;

@RestController
@RequestMapping("/api/report")
public class ReportAPI {

	@Autowired
	private ReportService reportService;
	
	
	@PostMapping
	public ResponseEntity<ResponseDTO> saveReport(@RequestBody ReportDTO reportDTO) {
		if (!Validate.ReportValidate(reportDTO)) {
			throw new FieldRequiredException();
		}
		ResponseDTO result = new ResponseDTO(null, reportService.save(reportDTO));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateReport(@RequestBody ReportDTO reportDTO, @PathVariable Long id) {
		if(!Validate.ReportValidate(reportDTO)) {
			throw new FieldRequiredException();
		}
		if (reportService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			reportDTO.setId(id);
			ResponseDTO result = new ResponseDTO(null, reportService.save(reportDTO));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteReport(@PathVariable Long id) {
		if (reportService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			reportService.deleteReport(id);
			ResponseDTO result = new ResponseDTO(null);
			result.setData("");
			return new ResponseEntity<ResponseDTO>(result,HttpStatus.OK);
		}

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getListReportS(@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "limit", required = true) Integer limit){
		ResponseDTO result = new ResponseDTO();
		Pageable pageable = PageRequest.of(page - 1, limit);
		result.setError(null);
		result.setData(reportService.getListReportsWithPaging(pageable));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseDTO> getReportById(@PathVariable Long id){
		if (reportService.findById(id) == null) {
			throw new NotExistedException();
		}
		ResponseDTO result = new ResponseDTO(null, reportService.findById(id));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
}
