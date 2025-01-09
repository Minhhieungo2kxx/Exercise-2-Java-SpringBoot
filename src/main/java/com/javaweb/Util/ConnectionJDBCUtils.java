package com.javaweb.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;


public class ConnectionJDBCUtils {

	
	
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "Hieuvlcm91";
	
	public static Connection getConnection() {
		Connection con=null;
		try {
			 con = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
}
