package com.ezdi.bmp.mysql;

import java.sql.Connection;
import java.sql.Statement;

import com.ezdi.bmp.utils.Constant;

public class QueryMySql
{
	public static Connection	mysqlConnection	= null;
	public static Statement		stmt			= null;

	//public static PreparedStatement	insertIntoBookmarkTablePS	= null;

	public QueryMySql() throws Exception
	{
		mysqlConnection = MySQLConnection.getConnection();
		stmt = mysqlConnection.createStatement();
		//insertIntoBookmarkTablePS = mysqlConnection.prepareStatement(Query.INSERT_INTO_BOOKMARK_TABLE);
	}

	public void insertEntryIntoBookmarkTable(String fileName, String subDirPath, String bookmarkNameStr, String demographicInfoStr)
	{
		// TODO Auto-generated method stub
		String query;
		try
		{
			if (bookmarkNameStr != null)
			{
				query = "insert into " + Constant.BOOKMARK_INFO_TABLE + " (FileName,SubDirPath," + bookmarkNameStr + ") values ('" + fileName + "','" + subDirPath + "'," + demographicInfoStr + ")";
			}
			else
			{
				query = "insert into " + Constant.BOOKMARK_INFO_TABLE + " (FileName,SubDirPath) values ('" + fileName + "','" + subDirPath + "')";
			}

			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
