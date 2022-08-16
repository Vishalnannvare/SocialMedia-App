package com.revature.socialmediaapp.model.util;
import java.sql.*;
import java.util.Properties;
import java.io.FileReader;
import java.sql.*;

public class ConnectionUtil {
	private static Connection con = null;
	
	public static Connection getconnection() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileReader("C:\\Users\\Vishal\\eclipse-workspace\\socialmediaapp\\src\\main\\resources\\application.properties"));
		
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		if(con==null)
		{
			con = DriverManager.getConnection(url,username,password);
		}
			return con;
		}
	}

