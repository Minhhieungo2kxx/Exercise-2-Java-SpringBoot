package com.javaweb.repository.ipl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.Joinable;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.Util.ConnectionJDBCUtils;
import com.javaweb.Util.NumberUtils;
import com.javaweb.Util.StringUtil;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.Entity.BuidingEntity;


@Repository
public class BuildingRepositoryimpl implements BuildingRepository {
	


	public static void joinTable(Map<String, Object> params,List<String> typeCode,StringBuilder sql) {
		String staffidLong=(String)params.get("staffid");
		
		if(staffidLong!=null&&staffidLong.equals("")) {
			sql.append("inner join assignmentbuilding on b.id=assignmentbuilding.buildingid ");
			
		}
		if(typeCode!=null &&typeCode.size()!=0) {
			sql.append("inner join buildingrenttype on b.id=buildingrenttype.buildingid ");
			sql.append("inner join renttype  on renttype.id=buildingrenttype.id ");
		
		String rentAreato=(String)params.get("areaTo");
		String rentAreafrom=(String)params.get("areaFrom");
		if(rentAreafrom!=null &&rentAreafrom.equals("")||rentAreato!=null&&rentAreato.equals("")) {
			sql.append("inner join rentarea on rentarea.buildingid=b.id ");
			
		}
	
			
		}
	}
	
	public static void queryNomal(Map<String, Object> params,StringBuilder where) {
		for (Map.Entry<String,Object> it : params.entrySet()) {
			if(!it.getKey().equals("staffId")&&!it.getKey().equals("typeCode")&&!it.getKey().startsWith("area")
					&&!it.getKey().startsWith("rentPrice")) {
				String value=it.getValue().toString();
				if(StringUtil.check(value)) {
					if(NumberUtils.isNumbber(value)==true) {
						where.append(" and b."+it.getKey()+"="+value);
					}
					else {
						where.append(" and b."+it.getKey()+"like '%"+value+"%' ");
					}
				}
				
			}
			
		}
	}
	
	public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String staffId = (String) params.get("staffId");
		if (StringUtil.check(staffId)) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}
		String rentAreaTo = (String) params.get("areaTo");
		String rentAreaFrom = (String) params.get("areaFrom");
		if (StringUtil.check(rentAreaFrom) == true || StringUtil.check(rentAreaTo) == true) {
			if (StringUtil.check(rentAreaFrom)) {
				where.append(" AND rentarea.value >=" + rentAreaFrom);
			}
			if (StringUtil.check(rentAreaTo)) {
				where.append(" AND rentarea.value <=" + rentAreaTo);
			}
		}
		String rentPriceTo = (String) params.get("rentPriceTo");
		String rentPriceFrom = (String) params.get("rentPriceFrom");
		if (StringUtil.check(rentPriceTo) == true || StringUtil.check(rentPriceFrom) == true) {
			if (StringUtil.check(rentAreaFrom)) {
				where.append(" AND b.rentprice >=" + rentPriceFrom);
			}
			if (StringUtil.check(rentAreaTo)) {
				where.append(" AND b.rentprice <=" + rentPriceTo);
			}
		}
		if (typeCode != null && typeCode.size() != 0) {
			List<String> code = new ArrayList<>();
			for (String item : typeCode) {
				code.add("'" + item + "'");
			}
			where.append(" AND renttype.code IN(" + String.join(",", code) + ")" );
		}

	}

	@Override
	public List<BuidingEntity> FindAll(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sql =new StringBuilder("SELECT b.id,b.name,b.districtid,b.street,b.ward,b.numberofbasement,b.floorarea,b.rentprice,b.managername,b.managerphonenumber,b.servicefee,b.brokeragefee,b.createddate  FROM building b  ");
		List<BuidingEntity> list = new ArrayList<>();
		
		joinTable(params,typeCode,sql);
        StringBuilder where=new StringBuilder("where 1=1 ");
        queryNomal(params, where);
        querySpecial(params, typeCode, where);
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
