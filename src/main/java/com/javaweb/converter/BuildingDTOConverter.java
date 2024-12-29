package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.javaweb.Builder.Buildingsearchbuilder;
import com.javaweb.Config.ModelMapperConfig;
import com.javaweb.Model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.Entity.BuidingEntity;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.repository.Entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {

	@Autowired
	private DistrictRepository dis;
	@Autowired
	private RentAreaRepository rent;
	@Autowired
	private ModelMapper mapper;

	
	public BuildingDTO tobuildingDto(BuidingEntity bd) {
		BuildingDTO buildto =mapper.map(bd,BuildingDTO.class);
//		buildto.setName(bd.getName());
		DistrictEntity di = dis.findnamebyid(bd.getDistrictid());
		buildto.setAddress(bd.getStreet() + "," + bd.getWard() + "," + di.getName());
		List<RentAreaEntity> rentareas = rent.getValuebyBuilding(bd.getId());
		String rentarearesult = rentareas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		buildto.setRentArea(rentarearesult);

		return buildto;
	}

}
