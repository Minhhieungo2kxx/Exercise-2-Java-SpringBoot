package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.repository.Entity.BuidingEntity;

public interface BuildingRepository {
     List<BuidingEntity> FindAll(Map<String, Object> params,List<String> typeCode);

	

	
     
}
