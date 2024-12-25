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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.Model.ErrorResponseDTO;
import com.javaweb.service.Buildingservice;

import customexception.FiledRequiredException;

//@Controller
@RestController // no bao gom ca cac phan Controller +co them Resonbody
public class BuildingAPI {
	
	@Autowired
	private Buildingservice service;

	@GetMapping(value = "/api/building")
	public List<BuildingDTO> getbuilding(@RequestParam Map<String,Object> params,
										@RequestParam(name = "typeCode",required = false)List<String>typeCode){
		List<BuildingDTO> result=service.FindAll(params,typeCode);
		return result;
	}
	
	
				
//	@GetMapping(value = "/api/building/")
//	public List<BuildingDTO> getbuilding(@RequestParam(value = "name",required = false)String name,
//			@RequestParam(value = "districtid",required = false)Integer districtid){
//		List<BuildingDTO> result=service.FindAll(name,districtid);
//		return result;
//		
//	}

	// deu su dung de giu tren pagram thuong de lam chuc nang tim kiem

//	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
//	public void getbuilding(@RequestParam(value = "name",required =false) String name
//			,@RequestParam(value="numberofBasement",required =false) Integer numberofBasement
//			,@RequestParam(value = "ward",required = false)String ward) {
//		     System.out.println(name+" "+numberofBasement+" "+ward);
//		     
//	    
//	}
//	 @RequestMapping(value = "/api/building/",method = RequestMethod.POST)
//     public void getbuilding2(@RequestParam Map<String,String> pagram) {
//		     System.out.println("ok ban oi"); 		    	
//	}
	// gui du lieu kieu body su dung Map va RequestBody
//     @RequestMapping(value = "/api/building/",method = RequestMethod.POST)
//     public void getbuilding2(@RequestBody Map<String,String> pagram) {
//		     System.out.println("ok ban oi"); 		    	
//	}

	// gui du lieu kieu body su dung JavaBean va RequestBody
	// day len pagram dung Map,day len body thi dung JavaBeans

//	@RequestMapping(value = "/api/building/",method = RequestMethod.POST)
//	public void getbuilding3(@RequestBody BuildingDTO dto) {
//		System.out.println("Da gui successful");
//	}
	// lay du lieu tu data de len Json nguoi dung(hieu la giao dien)
	// co 2 dang cau truc Json la: Map va JavaBeans deu co kieu la key-Value
//		@RequestMapping(value = "/api/building/", method = RequestMethod.GET)

////		@ResponseBody // su dung de tra du lieu lai cho nguoi dung kieu JSon
//		public BuildingDTO getbuilding(@RequestParam(value = "name", required = false) String name,
//				@RequestParam(value = "numberofBasement", required = false) String numberofBasement,
//				@RequestParam(value = "ward", required = false) String ward) {
//			
//			// gia su da xu ly xong du lieu tu database
//			BuildingDTO rs = new BuildingDTO();
//			rs.setName(name);
//			rs.setNumberBasement(numberofBasement);
//			rs.setWard(ward);
//			return rs;
//	}

	// cung co the su dung @get,put,post,delete Mapping co san anotation

//	@PostMapping(value = "/api/building/")
//	public void getbuilding3(@RequestBody BuildingDTO dto) {
//		System.out.println("Da gui successful");
//	}
//
//	// thuong lam chuc nang delete +ket hop voi ca requestParam
//	@DeleteMapping(value = "/api/building/{id}/{building}/")
//	public void deleteBuilding(@PathVariable Integer id, @PathVariable String building,
//			@RequestParam(value = "ward", required = false) String ward) {
//		System.out.println("Deleted Successful Building was " + id + " ok");

//	@GetMapping(value = "/api/building/")
//	public List<BuildingDTO> getbuilding(
//			@RequestParam(value = "name", required = false) String name,
//			@RequestParam(value = "numberofBasement", required = false) String numberofBasement,
//			@RequestParam(value = "ward", required = false) String ward){
//		
//		List<BuildingDTO>bd=new ArrayList<>();
//		BuildingDTO bd1=new BuildingDTO();
//		bd1.setName("Hong Kong Tower");
//		bd1.setNumberBasement("4");
//		bd1.setWard("Cau Giay-Ha Noi");
//		bd.add(bd1);
//		BuildingDTO bd2=new BuildingDTO();
//		bd2.setName("Nhat tan Offical");
//		bd2.setNumberBasement("5");
//		bd2.setWard("Vinh Ngoc-Ha Noi");
//		bd.add(bd2);
//		return bd;
//	}

	// xu ly truong hop phai tra ve chi tiet chinh loi day gay ra

//	@PostMapping(value = "/api/building/")
//	public Object getbuilding(@RequestBody BuildingDTO buiding) {
//
////		try {
////			System.out.print(5/0);
////			vadidate(buiding);
////		} catch (FiledRequiredException e) {
////
////			ErrorResponseDTO error = new ErrorResponseDTO();
////			error.setError(e.getMessage());
////			List<String> details = new ArrayList<>();
////			details.add("Loi dang o nam hoac numberBasement day check lai di");
////			error.setDetai(details);
////			return error;
////		}
//		vadidate(buiding);
//		return null;
//
//	}
//
//	public void vadidate(BuildingDTO dto) {
//		if (dto.getName() == null || dto.getName().equals("") || dto.getNumberBasement() == null) {
//			throw new FiledRequiredException("name or numberBasement dang bi rong hoac la null day");
//		}
//	}

}
