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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.DTO.SpaceDTO;
import com.RentalBuilding.Exception.ExistedException;
import com.RentalBuilding.Exception.FieldRequiredException;
import com.RentalBuilding.Exception.NotExistedException;
import com.RentalBuilding.Service.SpaceService;
import com.RentalBuilding.Utils.Validate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/space")
public class SpaceAPI {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SpaceService spaceService;
	@PostMapping
	public ResponseEntity<ResponseDTO> saveSpace(@RequestParam("img") MultipartFile file, @RequestParam("data") String jsonData) {
		SpaceDTO space = null;
			try {
				space = objectMapper.readValue(jsonData, SpaceDTO.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		if(!Validate.SpaceValidate(space)) {
			throw new FieldRequiredException();
		}
		if (space!=null && spaceService.findBySpaceCode(space.getSpaceCode()) != null) {
			throw new ExistedException();
		} else {
			ResponseDTO result = new ResponseDTO(null, spaceService.save(space,file));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateSpace(@RequestParam(name = "img", required = false) MultipartFile file,@RequestParam("data") String jsonData, @PathVariable Long id) {
		SpaceDTO space = null;
		if (spaceService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			try {
				space = objectMapper.readValue(jsonData, SpaceDTO.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			if(!Validate.SpaceValidate(space)) {
				throw new FieldRequiredException();
			}
			space.setId(id);
			ResponseDTO result = new ResponseDTO(null, spaceService.save(space,file));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteSpace(@PathVariable Long id) {
		if (spaceService.findById(id) == null) {
			throw new NotExistedException();
		} 
		else {
			spaceService.deleteSpace(id);
			ResponseDTO result = new ResponseDTO(null);
			result.setData("");
			return new ResponseEntity<ResponseDTO>(result,HttpStatus.OK);
		}

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getListSpaces(@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "limit", required = false) Integer limit){
		ResponseDTO result = new ResponseDTO();
		if(page == null && limit == null) {
			result.setError(null);
			result.setData(spaceService.getListSpaces());
		} else {
			Pageable pageable = PageRequest.of(page - 1, limit);
			result.setError(null);
			result.setData(spaceService.getListSpacesWithPaging(pageable));
		}
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseDTO> getSpaceById(@PathVariable Long id){
		if (spaceService.findById(id) == null) {
			throw new NotExistedException();
		}
		ResponseDTO result = new ResponseDTO(null, spaceService.findById(id));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/contractComingToEnd")
	public ResponseEntity<ResponseDTO> contractComingToEnd(){
		ResponseDTO result = new ResponseDTO(null, spaceService.getSpacesThatContractComingToEnd());
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/searchByField")
	public ResponseEntity<ResponseDTO> searchByField(@RequestParam(value = "spaceCode", required =false) String spaceCode, 
			@RequestParam(value = "spaceAreaFrom", required =false) Integer spaceAreaFrom, 
			@RequestParam(value = "spaceAreaTo", required =false) Integer spaceAreaTo,
			@RequestParam(value = "spacePriceFrom", required =false) Integer spacePriceFrom,
			@RequestParam(value = "spacePriceTo", required =false) Integer spacePriceTo,
			@RequestParam(value = "floorName", required =false) String floorName,
			@RequestParam(value = "spaceStatusName", required =false) String spaceStatusName,
			@RequestParam(value = "spaceTypeName", required =false) String spaceTypeName){
		ResponseDTO result = new ResponseDTO(null, spaceService.searchByField(spaceCode, spaceAreaFrom, spaceAreaTo, spacePriceFrom, spacePriceTo, 
				floorName, spaceStatusName, spaceTypeName));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
}
