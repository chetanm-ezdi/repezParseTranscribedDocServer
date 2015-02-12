package com.ezdi.dip.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import com.ezdi.dip.deidentification.Deidentification;
import com.ezdi.dip.mysql.QueryMySql;

public class FileUtils
{
	private static FileReader		fr;
	private static BufferedReader	br;
	private static FileWriter		fw;

	private static Set<String>		modifiedFileContentSet;

	private static QueryMySql		queryMySqlObj;
	private static Deidentification	deidentificationObj;

	private static String			line;

	public FileUtils() throws Exception
	{
		queryMySqlObj = new QueryMySql();
		deidentificationObj = new Deidentification();
	}

	public void fileProcess(String fileName, String tenantCode) throws IOException
	{
		// TODO Auto-generated method stub
		queryMySqlObj.getBookmarkValueFromTable(fileName);
		readFile(fileName, tenantCode);
		writeFile(fileName);
	}

	private void readFile(String fileName, String tenantCode) throws IOException
	{
		// TODO Auto-generated method stub
		fr = new FileReader(Constant.ROOT + "/" + tenantCode + "/" + fileName);
		br = new BufferedReader(fr);
		while ((line = br.readLine()) != null)
		{
			line = deidentificationObj.getReplacedLine(line);
			modifiedFileContentSet.add(line);
		}
	}

	private void writeFile(String fileName) throws IOException
	{
		// TODO Auto-generated method stub
		fw = new FileWriter(Constant.DEIDENTIFIEDFILES + "/" + fileName);
		for (String updatedLine : modifiedFileContentSet)
		{
			fw.write(updatedLine + "\n");
		}
		fw.close();
	}

}
