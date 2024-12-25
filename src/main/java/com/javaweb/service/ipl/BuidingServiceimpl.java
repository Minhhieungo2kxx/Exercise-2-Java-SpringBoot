package com.javaweb.service.ipl;

import java.util.*;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.Entity.BuidingEntity;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.repository.Entity.RentAreaEntity;
import com.javaweb.service.Buildingservice;

@Service
public class BuidingServiceimpl implements Buildingservice {
   
	@Autowired
	BuildingDTOConverter converter;
	@Autowired
	private BuildingRepository repository;
	public List<BuildingDTO> FindAll(Map<String, Object> params, List<String> typeCode) {
		List<BuildingDTO> dto=new ArrayList<>();
		List<BuidingEntity> entities=repository.FindAll(params,typeCode);
		
		for(BuidingEntity bd:entities) {
			BuildingDTO building=converter.tobuildingDto(bd);
			dto.add(building);
			
		}
		return dto;
	}
      
}
