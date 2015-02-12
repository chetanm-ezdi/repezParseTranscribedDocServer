package com.ezdi.dip.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import com.ezdi.dip.mysql.QueryMySql;

public class FileUtils
{
	public static FileReader		fr;
	public static BufferedReader	br;

	public static Set<String>		demographicInfoSet;
	public static Set<String>		modifiedFileLineSet;

	public static String			replaceStr;

	private static QueryMySql		queryMySqlObj;

	public FileUtils() throws Exception
	{
		queryMySqlObj = new QueryMySql();
	}

	public void fileProcess(String fileName, String tenantCode) throws IOException
	{
		// TODO Auto-generated method stub
		readFile(fileName, tenantCode);
		writeFile(fileName);
	}

	public void readFile(String fileName, String tenantCode) throws IOException
	{
		// TODO Auto-generated method stub

		queryMySqlObj.getDemographicInfoFromTable(fileName);

		fr = new FileReader(Constant.ROOT + "/" + tenantCode + "/" + fileName);
		br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null)
		{
			for (String bookmarkVal : QueryMySql.demographicInfoSet)
			{
				if (line.contains(bookmarkVal))
				{
					getReplaceStr(bookmarkVal.length());
					line = line.replace(bookmarkVal, replaceStr);
				}
			}
			modifiedFileLineSet.add(line);
		}
	}

	private void getReplaceStr(int strLength)
	{
		// TODO Auto-generated method stub
		replaceStr = "";
		for (int i = 0; i < strLength; i++)
		{
			replaceStr = replaceStr.concat(Constant.REPLACECHAR);
		}
	}

	private void writeFile(String fileName) throws IOException
	{
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter(Constant.DEIDENTIFIEDFILES + "/" + fileName);
		for (String updatedLine : modifiedFileLineSet)
		{
			fw.write(updatedLine + "\n");
		}
		fw.close();
	}

}
