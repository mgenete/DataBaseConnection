package com.db.config.test;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.testng.annotations.Test;

import com.db.config.DataBaseConfig;
import com.db.config.SqlServerDBConfig;



public class DBconfigTest  {
	
	String connString = "jdbc:sqlserver://localhost:1433;instanceName=Matti-PC\\QA;databaseName=RandomHouse;integratedSecurity=true;";

	@Test
	public void dbConfigTest() throws ClassNotFoundException, SQLException  {
		
		DataBaseConfig config = new SqlServerDBConfig();
		//config.setConnectionString(connString).executeUpdate("insert into Sales.Customer values (502,'Star Books','s@aaol.com') ");
		//config.setConnectionString(connString).executeUpdate("delete from Sales.Customer where CustomerID=501");
		//config.setConnectionString(connString).executeUpdate("update Sales.Customer set CustomerName='WaWa Books' where CustomerID=502");
		LinkedHashMap<Integer, LinkedHashMap<String, String>> testData = config.setConnectionString(connString).executeQuery("select  CustomerName, CustomerEmail from Sales.Customer where CustomerID=502");
		System.out.println(testData.toString());
	}

}
