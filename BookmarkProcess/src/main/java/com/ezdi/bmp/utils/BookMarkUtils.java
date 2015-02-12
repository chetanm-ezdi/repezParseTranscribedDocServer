package com.ezdi.bmp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookMarkUtils
{
	/* get files from input folder */
	public static Map<String, String>	bookmarkMap	= new LinkedHashMap<String, String>();

	public static void getConsideredBookmarkMap()
	{
		String line;
		try
		{
			File file = new File("src/main/resources/Bookmarks.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null)
			{
				String columns[] = line.split("\t");
				String bookmarkArray[] = columns[0].split("/");
				for (String bookmark : bookmarkArray)
				{
					bookmarkMap.put(bookmark.trim(), columns[1].trim());
				}

			}
			br.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
