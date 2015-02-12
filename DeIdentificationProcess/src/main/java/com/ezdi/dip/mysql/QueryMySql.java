package com.ezdi.dip.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.ezdi.dip.utils.Constant;

public class QueryMySql
{
	public static Connection	mysqlConnection	= null;
	public static Statement		stmt			= null;
	public static Set<String>	demographicInfoSet;

	public QueryMySql() throws Exception
	{
		mysqlConnection = MySQLConnection.getConnection();
		stmt = mysqlConnection.createStatement();
	}

	public void getDemographicInfoFromTable(String fileName)
	{
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		ResultSetMetaData rsmd = null;
		demographicInfoSet = new HashSet<String>();
		try
		{
			String query = "select * from " + Constant.BOOKMARK_INFO_TABLE + " where FileName = '" + fileName + "'";
			resultSet = stmt.executeQuery(query);
			if (resultSet != null)
			{
				while (resultSet.next())
				{
					rsmd = resultSet.getMetaData();
					int columnnumber = rsmd.getColumnCount();
					for (int i = 2; i < columnnumber; i++)
					{
						demographicInfoSet.add(resultSet.getString(i));
					}
				}
			}
			resultSet.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
