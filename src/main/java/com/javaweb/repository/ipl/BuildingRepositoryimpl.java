package com.javaweb.repository.ipl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.Joinable;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.javaweb.Builder.Buildingsearchbuilder;
import com.javaweb.Model.BuildingDTO;
import com.javaweb.Util.ConnectionJDBCUtils;
import com.javaweb.Util.NumberUtils;
import com.javaweb.Util.StringUtil;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.Entity.BuidingEntity;


@Repository
public class BuildingRepositoryimpl implements BuildingRepository {
	


	public static void joinTable(Buildingsearchbuilder buildingsearch,StringBuilder sql) {
		Long staffidLong=(Long)(buildingsearch.getStaffId());
		
		if(staffidLong!=null){
			sql.append("inner join assignmentbuilding on b.id=assignmentbuilding.buildingid ");
			
		}
		if(buildingsearch.getTypeCode()!=null &&buildingsearch.getTypeCode().size()!=0) {
			sql.append("inner join buildingrenttype on b.id=buildingrenttype.buildingid ");
			sql.append("inner join renttype  on renttype.id=buildingrenttype.id ");
		
		
			
		}
			
		}
	
	
	public static void queryNomal(Buildingsearchbuilder buildingsearchbuilder,StringBuilder where) {

		try {
			Field[] fields=Buildingsearchbuilder.class.getDeclaredFields();
			for(Field item:fields) {
				item.setAccessible(true);
				String filedname=item.getName();
				if(!filedname.equals("staffId")&&!filedname.equals("typeCode")&&!filedname.startsWith("area")
						&&!filedname.startsWith("rentPrice")) {
					Object value=item.get(buildingsearchbuilder);
					if(value!=null) {
						if(item.getType().getName().equals("java.lang.Long")||item.getType().getName().equals("java.lang.Interger")) {
							where.append(" and b."+filedname+"="+value);
						}
						else if(item.getType().getName().equals("java.lang.String")) {
							where.append(" and b."+filedname+"like '%"+value+"%' ");
						}
					}
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void querySpecial(Buildingsearchbuilder buildingsearch, StringBuilder where) {
		Long staffId = (Long) buildingsearch.getStaffId();
		if (staffId!=null) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}
		Long AreaTo = (Long)buildingsearch.getAreaTo();
		Long AreaFrom = (Long)buildingsearch.getAreaFrom();
		if (AreaTo!=null || AreaTo !=null) {
			if (AreaFrom!=null) {
				where.append(" AND rentarea.value >=" + AreaFrom);
			}
			if (AreaTo!=null) {
				where.append(" AND rentarea.value <=" +AreaTo);
			}
		}
		Long rentPriceTo =buildingsearch.getRentPriceTo();
		Long rentPriceFrom = buildingsearch.getRentPriceFrom();
		if (rentPriceTo!=null || rentPriceFrom !=null) {
			if (rentPriceFrom !=null) {
				where.append(" AND b.rentprice >=" + rentPriceFrom);
			}
			if (rentPriceTo!=null) {
				where.append(" AND b.rentprice <=" + rentPriceTo);
			}
		}
		List<String>typeCode=buildingsearch.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			List<String> code = new ArrayList<>();
			for (String item : typeCode) {
				code.add("'" + item + "'");
			}
			where.append(" AND renttype.code IN(" + String.join(",", code) + ")" );
		}

	}

	@Override
	public List<BuidingEntity> FindAll(Buildingsearchbuilder buildingsearchbuilder) {
		StringBuilder sql =new StringBuilder("SELECT b.id,b.name,b.districtid,b.street,b.ward,b.numberofbasement,b.floorarea,b.rentprice,b.managername,b.managerphonenumber,b.servicefee,b.brokeragefee,b.createddate  FROM building b  ");
		List<BuidingEntity> list = new ArrayList<>();
		
		joinTable(buildingsearchbuilder,sql);
        StringBuilder where=new StringBuilder("where 1=1 ");
        queryNomal(buildingsearchbuilder, where);
        querySpecial(buildingsearchbuilder, where);
        where.append("group by b.id");
        sql.append(where);
		
		try (Connection con = ConnectionJDBCUtils.getConnection();

				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql.toString());) {

			while (rs.next()) {
				BuidingEntity bd =new BuidingEntity();
				bd.setId(rs.getLong("b.id"));
				bd.setName(rs.getString("b.name"));
				bd.setWard(rs.getString("b.ward"));
				bd.setDistrictid(rs.getLong("b.districtid"));
				bd.setStreet(rs.getString("b.street"));
				bd.setFloorArea(rs.getLong("b.floorarea"));
				bd.setRentPrice(rs.getLong("b.rentprice"));
				bd.setServiceFee(rs.getString("b.servicefee"));
				bd.setBrokerageFee (rs.getLong("b.brokeragefee"));
				bd.setManagerName(rs.getString("b.managername"));
				bd.setManagerPhone(rs.getString("b.managerphonenumber"));
				list.add(bd);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return list;
	}
}
