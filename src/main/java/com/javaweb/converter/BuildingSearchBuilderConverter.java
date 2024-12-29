package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.Builder.Buildingsearchbuilder;
import com.javaweb.Util.MapUtil;

@Component
public class BuildingSearchBuilderConverter {

	public Buildingsearchbuilder toBuildingSearchBuilder(Map<String,Object>params,List<String> typeCode) {
		Buildingsearchbuilder buildingsearchbuilder=new Buildingsearchbuilder.Builder()
				.setName (MapUtil.getObject(params, "name", String.class))
				.setFloorArea (MapUtil.getObject(params, "floorArea", Long.class))
				.setWard(MapUtil.getObject(params, "ward", String.class))
				.setStreet (MapUtil.getObject(params, "street", String.class))
				.setDistrictcode (MapUtil.getObject(params, "districtcode", Long.class))
				.setNumberOfBasement (MapUtil.getObject(params, "numberofbasement", Integer.class))
				.setTypeCode(typeCode)
				.setManagerName (MapUtil.getObject(params, "managername", String.class))
				.setManagerPhoneNumber (MapUtil.getObject(params, "managerphonenumber", String.class))
				.setRentPriceTo(MapUtil.getObject(params, "rentpriceto", Long.class))
				.setRentPriceFrom(MapUtil.getObject(params, "rentpricefrom", Long.class))
				.setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
				.setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
				.setStaffId(MapUtil.getObject(params, "staffId", Long.class))
				.build();
				
					
		
		return buildingsearchbuilder ;
	}
}
