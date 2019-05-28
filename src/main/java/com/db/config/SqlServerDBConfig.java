package com.db.config;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class SqlServerDBConfig implements DataBaseConfig, Closeable{

	//String connectionString_2 = "jdbc:sqlserver://localhost:1433;databaseName=SqlData;user=sa;password=admin@1234#;integratedSecurity=false;";
	public String connString;

	
	public DataBaseConfig setConnectionString(String connString) {
		this.connString=connString;
		return this;
	}
	
	
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(connString);
	}
	
	//**
	private Statement getStatement() throws ClassNotFoundException, SQLException {
		return getConnection().createStatement();
	}

	public int executeUpdate(String sqlQuery) throws ClassNotFoundException, SQLException {
		return getStatement().executeUpdate(sqlQuery);
		
	}
	
	//**
	private String[] getColumnName(ResultSet result) throws SQLException {
		ResultSetMetaData data = result.getMetaData();
		String[] columnName = new String[data.getColumnCount()];
		
		
		for(int i=1; i<=data.getColumnCount(); i++) {
			columnName[i=1] = data.getColumnClassName(i);
		}
		return columnName;
	}
	
	//**
	//to find the data type of column to get column value
	private String getColumnDataType(int i, ResultSet result) throws SQLException {
		int type = result.getMetaData().getColumnType(i+1);
		
		switch(type) {
		case Types.VARCHAR:
			return result.getString(i+1);
		case Types.NUMERIC:
			return result.getInt(i+1)+"";
		}
		return null;
	}
	
	//**
	private LinkedHashMap<String, String> getDataBaseData(String[] columnName, ResultSet result) throws SQLException {
		LinkedHashMap<String, String> columnData = new LinkedHashMap<String, String>();
		
		for(int i=0; i<columnName.length; i++) {
			columnData.put(columnName[i], getColumnDataType(i, result));
		}
		return columnData;
	}

	public LinkedHashMap<Integer, LinkedHashMap<String, String>> executeQuery(String sqlQuery) throws ClassNotFoundException, SQLException {
		ResultSet result = getStatement().executeQuery(sqlQuery);
		String[] columnName = getColumnName(result);
		
		LinkedHashMap<Integer, LinkedHashMap<String, String>> dbData = new LinkedHashMap<Integer, LinkedHashMap<String,String>>();
		int counter = 1;
		while(result.next()) {
			dbData.put(counter,  getDataBaseData(columnName, result));
			counter++;
		}
		return dbData;
		
	}
	
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
