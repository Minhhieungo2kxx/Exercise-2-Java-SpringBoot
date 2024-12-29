package com.javaweb.service.ipl;

import java.util.*;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Builder.Buildingsearchbuilder;
import com.javaweb.Model.BuildingDTO;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
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
	
	@Autowired
	BuildingSearchBuilderConverter builderConverter;
	public List<BuildingDTO> FindAll(Map<String,Object>params,List<String>typeCode) {
		List<BuildingDTO> dto=new ArrayList<>();
		Buildingsearchbuilder buildersearch=builderConverter.toBuildingSearchBuilder(params,typeCode);
		List<BuidingEntity> entities=repository.FindAll(buildersearch);
		
		for(BuidingEntity bd:entities) {
			BuildingDTO building=converter.tobuildingDto(bd);
			dto.add(building);
			
		}
		return dto;
	}
      
}
