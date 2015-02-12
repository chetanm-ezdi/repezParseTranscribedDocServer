package com.ezdi.dip.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import com.ezdi.dip.utils.Constant;

public class QueryMySql
{
	private static Connection			mysqlConnection	= null;
	private static Statement			stmt			= null;

	private static String				query;

	private static ResultSet			resultSet;
	private static ResultSetMetaData	resultSetMetaData;

	public static Set<String>			bookmarkValSet;

	public QueryMySql() throws Exception
	{
		mysqlConnection = MySQLConnection.getConnection();
		stmt = mysqlConnection.createStatement();
	}

	public void getBookmarkValueFromTable(String fileName)
	{
		// TODO Auto-generated method stub
		resultSet = null;
		resultSetMetaData = null;
		bookmarkValSet = new LinkedHashSet<String>();
		try
		{
			query = "select * from " + Constant.BOOKMARK_INFO_TABLE + " where FileName = '" + fileName + "'";
			resultSet = stmt.executeQuery(query);
			if (resultSet != null)
			{
				while (resultSet.next())
				{
					resultSetMetaData = resultSet.getMetaData();
					int columnnumber = resultSetMetaData.getColumnCount();
					for (int i = 2; i < columnnumber; i++)
					{
						bookmarkValSet.add(resultSet.getString(i));
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
