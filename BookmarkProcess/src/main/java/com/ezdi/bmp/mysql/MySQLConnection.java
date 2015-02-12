package com.ezdi.bmp.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ezdi.bmp.utils.Constant;

public class MySQLConnection
{
	private static Connection	connection	= null;
	private static String		url			= null;
	private static String		driver_name	= null;
	private static String		user_name	= null;
	private static String		password	= null;

	public static Connection getConnection()
	{
		try
		{
			url = Constant.MYSQL_URL;
			driver_name = Constant.MYSQL_DRIVER_CLASS_NAME;
			user_name = Constant.MYSQL_USERNAME;
			password = Constant.MYSQL_PASSWORD;
			Class.forName(driver_name);
			connection = DriverManager.getConnection(url, user_name, password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return connection;
	}
}