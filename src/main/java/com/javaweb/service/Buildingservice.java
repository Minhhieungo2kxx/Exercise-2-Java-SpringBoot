package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.Builder.Buildingsearchbuilder;
import com.javaweb.Model.BuildingDTO;
import com.javaweb.repository.Entity.BuidingEntity;

public interface Buildingservice {
	 List<BuildingDTO> FindAll(Map<String,Object>params,List<String>typeCode);

	
	
}
