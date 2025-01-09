package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scripting.bsh.BshScriptUtils.BshExecutionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.Model.BuildingDTORequest;
import com.javaweb.Model.ErrorResponseDTO;
import com.javaweb.repository.Entity.BuidingEntity;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.service.Buildingservice;

import customexception.FiledRequiredException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@RestController // no bao gom ca cac phan Controller +co them Resonbody

@PropertySource("classpath:application.properties")
@Transactional
public class BuildingAPI {
	
	@Autowired
	private Buildingservice service;

	@PersistenceContext
	EntityManager entityManager;
	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> getbuilding(@RequestParam Map<String,Object> params,
										@RequestParam(name = "typeCode",required = false)List<String>typeCode){
		List<BuildingDTO> result=service.FindAll(params,typeCode);
		return result;
	}
	
	@PostMapping(value = "/api/building/")
	public void CreateBuilding(@RequestBody BuildingDTORequest buildingDTORequest) {
		BuidingEntity bd=new BuidingEntity();
		bd.setName(buildingDTORequest.getName());
		bd.setStreet(buildingDTORequest.getStreet());
		bd.setWard(buildingDTORequest.getWard());
		DistrictEntity dis=new DistrictEntity();
		dis.setId(buildingDTORequest.getDistrictid());
		bd.setDistrict(dis);
		entityManager.persist(bd);
		System.out.print("OK them thanh cong");
			
		
	}
	
	@PutMapping(value = "/api/building/")
    public void updateBuilding(@RequestBody BuildingDTORequest buildingDTORequest) {
      BuidingEntity bd = entityManager.find(BuidingEntity.class,buildingDTORequest.getId());
        if (bd == null) {
            throw new RuntimeException("Building not found");
        }
        bd.setName(buildingDTORequest.getName());
        bd.setStreet(buildingDTORequest.getStreet());
        bd.setWard(buildingDTORequest.getWard());

        DistrictEntity dis = new DistrictEntity();
        dis.setId(buildingDTORequest.getDistrictid());
        bd.setDistrict(dis);
        entityManager.merge(bd);
        System.out.print("OK sua thanh cong");
    }
	
	@DeleteMapping(value = "/api/building/{id}")
    public void DeleteBuilding(@PathVariable Long id) {
		BuidingEntity bd=entityManager.find(BuidingEntity.class, id);
		if (bd == null) {
            throw new RuntimeException("Building not found");
        }
		    entityManager.remove(bd);
	        System.out.print("OK xoa thanh cong");
				
    }
	
	
	
	

}
