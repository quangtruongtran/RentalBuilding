package com.RentalBuilding.Service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.RentalBuilding.DTO.SpaceDTO;
import com.RentalBuilding.Entity.SpaceEntity;

public interface SpaceService {

	SpaceEntity findBySpaceCode(String spaceCode);
	SpaceDTO save(SpaceDTO space, MultipartFile file);
	SpaceDTO findById(Long id);
	void deleteSpace(Long id);
	List<SpaceDTO> getListSpaces();
	List<SpaceDTO> getListSpacesWithPaging(Pageable pageable);
	List<SpaceDTO> getSpacesThatContractComingToEnd();
	Set<SpaceDTO> searchByField(String spaceCode, Integer spaceAreaFrom, Integer spaceAreaTo, Integer spacePriceFrom, 
			Integer spacePriceTo, String floorName, String spaceStatusName, String spaceTypeName);
}
