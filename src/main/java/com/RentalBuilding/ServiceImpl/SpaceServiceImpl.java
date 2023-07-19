package com.RentalBuilding.ServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.RentalBuilding.Converter.SpaceConverter;
import com.RentalBuilding.CustomRepository.SpaceRepositoryCustom;
import com.RentalBuilding.DTO.SpaceDTO;
import com.RentalBuilding.Entity.SpaceEntity;
import com.RentalBuilding.Repository.FloorRepository;
import com.RentalBuilding.Repository.SpaceRepository;
import com.RentalBuilding.Repository.SpaceStatusRepository;
import com.RentalBuilding.Repository.SpaceTypeRepository;
import com.RentalBuilding.Service.SpaceService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
@Transactional
public class SpaceServiceImpl implements SpaceService{
	
	@Autowired
	private SpaceRepository spaceRepository;
	
	@Autowired
	private FloorRepository floorRepository;
	
	@Autowired
	private SpaceStatusRepository spaceStatusRepository;
	
	@Autowired
	private SpaceTypeRepository spaceTypeRepository;
	
	@Autowired
	private SpaceRepositoryCustom spaceRepositoryCustom;
	
	@Autowired
	private Cloudinary cloudinary;
	
	@Override
	public SpaceEntity findBySpaceCode(String spaceCode) {
		return spaceRepository.findBySpaceCode(spaceCode);
	}

	@Override
	public SpaceDTO save(SpaceDTO space, MultipartFile file) {
		String imgLink = null;
		if (space.getId() == null) {
			SpaceEntity spaceSave = SpaceConverter.DTOtoEntity(space);
			spaceSave.setFloor(floorRepository.findByFloorName(space.getFloorName()));
			spaceSave.setSpaceStatus(spaceStatusRepository.findBySpaceStatusName(space.getSpaceStatusName()));
			spaceSave.setSpaceType(spaceTypeRepository.findBySpaceTypeName(space.getSpaceTypeName()));
			try {
				Map r = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
				imgLink = (String) r.get("secure_url");
			} catch (IOException e) {
				e.printStackTrace();
			}
			spaceSave.setSpaceImage(imgLink);
			
				return SpaceConverter.EntitytoDTO( spaceRepository.save(spaceSave));

		} else if (space.getId() != null) {
			SpaceEntity updateSpace = spaceRepository.findById(space.getId()).get();
			SpaceConverter.updateEntity(updateSpace, space);
			updateSpace.setFloor(floorRepository.findByFloorName(space.getFloorName()));
			updateSpace.setSpaceStatus(spaceStatusRepository.findBySpaceStatusName(space.getSpaceStatusName()));
			updateSpace.setSpaceType(spaceTypeRepository.findBySpaceTypeName(space.getSpaceTypeName()));
			if(file != null) {
				if (!file.isEmpty()) {
					try {
						Map r = this.cloudinary.uploader().upload(file.getBytes(),
								ObjectUtils.asMap("resource_type", "auto"));
						imgLink = (String) r.get("secure_url");
					} catch (IOException e) {
						e.printStackTrace();
					}
					updateSpace.setSpaceImage(imgLink);
				}
			}
			return SpaceConverter.EntitytoDTO(spaceRepository.save(updateSpace));
		}
		return null;
	}


	@Override
	public void deleteSpace(Long id) {
		spaceRepository.deleteById(id);
	}

	@Override
	public SpaceDTO findById(Long id) {
		Optional<SpaceEntity> space = spaceRepository.findById(id);
		if(!space.isPresent()) {
			return null;
		} 
		return SpaceConverter.EntitytoDTO(space.get());
	}

	@Override
	public List<SpaceDTO> getListSpaces() {
		List<SpaceDTO> results = new ArrayList<>();
		for (SpaceEntity space : spaceRepository.findAll()) {
			results.add(SpaceConverter.EntitytoDTO(space));
		}
		return results;
	}

	@Override
	public List<SpaceDTO> getListSpacesWithPaging(Pageable pageable) {
		List<SpaceDTO> results = new ArrayList<>();
		for (SpaceEntity space : spaceRepository.findAll(pageable).getContent()) {
			results.add(SpaceConverter.EntitytoDTO(space));
		}
		return results;
	}

	@Override
	public List<SpaceDTO> getSpacesThatContractComingToEnd() {
		List<SpaceDTO> list = new ArrayList<>();
		for (SpaceEntity space : spaceRepository.getSpacesThatContractComingToEnd()) {
			list.add(SpaceConverter.EntitytoDTO(space));
		}
		return list;
	}

	@Override
	public Set<SpaceDTO> searchByField(String spaceCode, Integer spaceAreaFrom, Integer spaceAreaTo,
			Integer spacePriceFrom, Integer spacePriceTo, String floorName, String spaceStatusName,
			String spaceTypeName) {
		return spaceRepositoryCustom.searchByField(spaceCode, spaceAreaFrom, spaceAreaTo, spacePriceFrom, spacePriceTo, 
				floorName, spaceStatusName, spaceTypeName);
	}
}
