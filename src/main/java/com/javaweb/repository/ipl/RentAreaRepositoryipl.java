package com.javaweb.repository.ipl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.Util.ConnectionJDBCUtils;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.repository.Entity.RentAreaEntity;


@Repository
public class RentAreaRepositoryipl implements RentAreaRepository {

	@Override
	public List<RentAreaEntity> getValuebyBuilding(Long id) {
		
		
		String sql = "SELECT * FROM rentarea  WHERE rentarea.buildingid = "+ id;
		List<RentAreaEntity> list=new ArrayList<>();
		try (Connection conn = ConnectionJDBCUtils.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
		while(rs.next()) {
			RentAreaEntity rent=new RentAreaEntity();
			rent.setValue("value");
			
			list.add(rent);
			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
