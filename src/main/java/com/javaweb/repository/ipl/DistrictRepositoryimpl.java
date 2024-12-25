package com.javaweb.repository.ipl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.Util.ConnectionJDBCUtils;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.Entity.BuidingEntity;
import com.javaweb.repository.Entity.DistrictEntity;

@Repository
public class DistrictRepositoryimpl implements DistrictRepository {

	

	@Override
	public DistrictEntity findnamebyid(Long id) {
		
			String sql = "SELECT d.name FROM district d WHERE d.id = "+ id + ";";
			DistrictEntity districtEntity = new DistrictEntity();
			try (Connection conn = ConnectionJDBCUtils.getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql)) {
			while(rs.next()) {
				
				districtEntity.setName(rs.getString("name"));
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return districtEntity;
	
	}
}


