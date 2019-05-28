package com.db.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public interface DataBaseConfig {
	
	
	public DataBaseConfig setConnectionString(String connString);
	public int executeUpdate(String sqlQuery) throws ClassNotFoundException, SQLException;
	public LinkedHashMap<Integer, LinkedHashMap<String, String>> executeQuery(String sqlQuery) throws ClassNotFoundException, SQLException;
	

}
