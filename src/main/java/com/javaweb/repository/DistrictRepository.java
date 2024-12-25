package com.javaweb.repository;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.Entity.BuidingEntity;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.repository.ipl.DistrictRepositoryimpl;


public  interface DistrictRepository {

	DistrictEntity findnamebyid(Long id);
	
    
}
