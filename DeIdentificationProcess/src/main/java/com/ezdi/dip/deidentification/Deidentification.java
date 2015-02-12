package com.ezdi.dip.deidentification;

import com.ezdi.dip.mysql.QueryMySql;
import com.ezdi.dip.utils.Constant;

public class Deidentification
{
	private static String	replaceStr;

	public String getReplacedLine(String line)
	{
		// TODO Auto-generated method stub
		for (String bookmarkVal : QueryMySql.bookmarkValSet)
		{
			if (line.contains(bookmarkVal))
			{
				getReplaceStr(bookmarkVal.length());
				line = line.replace(bookmarkVal, replaceStr);
			}
		}
		return line;

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

}
