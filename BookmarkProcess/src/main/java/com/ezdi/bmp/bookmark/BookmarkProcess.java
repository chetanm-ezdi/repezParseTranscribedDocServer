package com.ezdi.bmp.bookmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Bookmark;
import org.apache.poi.hwpf.usermodel.Bookmarks;

import com.ezdi.bmp.mysql.QueryMySql;
import com.ezdi.bmp.utils.BookMarkUtils;

public class BookmarkProcess
{
	private static Bookmark				bookmark;
	private static HWPFDocument			document;

	private static QueryMySql			queryMySqlObj;

	private static Map<String, String>	bookmarkDemographicInfoMap;

	private static String				bookmarkName;
	private static String				bookmarkValue;
	private static String				bookmarkNameStr;
	private static String				tempbookmarkNamestr;
	private static String				demogrphicInfoStr;
	private static String				tempDemogrphicInfoStr;
	private static String				docFileName;
	private static FileInputStream		fileInputStream;

	private static int					ind;

	public BookmarkProcess() throws Exception
	{
		BookMarkUtils.getConsideredBookmarkMap();
		queryMySqlObj = new QueryMySql();
	}

	public void getDemographicInfoFromBookmarks(File docFile, String dirPath) throws IOException
	{
		// TODO Auto-generated method stub
		docFileName = docFile.getName();
		fileInputStream = new FileInputStream(docFile);
		getBookmarkDemographicInfoMap(fileInputStream);
		getBookmarkNameStr(bookmarkDemographicInfoMap);
		getDemographicInfoStr(bookmarkDemographicInfoMap);
		queryMySqlObj.insertEntryIntoBookmarkTable(docFileName, dirPath, bookmarkNameStr, demogrphicInfoStr);

	}

	private void getBookmarkDemographicInfoMap(FileInputStream fileInputStream) throws IOException
	{
		// TODO Auto-generated method stub
		bookmarkDemographicInfoMap = new LinkedHashMap<String, String>();

		document = new HWPFDocument(fileInputStream);
		Bookmarks bookmarks = document.getBookmarks();

		for (int k = 0; k < bookmarks.getBookmarksCount(); k++)
		{
			bookmark = bookmarks.getBookmark(k);
			bookmarkName = bookmark.getName().trim();

			if (BookMarkUtils.bookmarkMap.containsKey(bookmarkName))
			{
				bookmarkValue = document.getText().subSequence(bookmark.getStart(), bookmark.getEnd()).toString().trim();
				bookmarkDemographicInfoMap.put(BookMarkUtils.bookmarkMap.get(bookmarkName), bookmarkValue);
			}
		}

	}

	private void getBookmarkNameStr(Map<String, String> bookmarkDemographicInfoMap)
	{
		// TODO Auto-generated method stub
		bookmarkNameStr = "";

		if (bookmarkDemographicInfoMap != null && !bookmarkDemographicInfoMap.isEmpty())
		{
			for (String bookmarkName : bookmarkDemographicInfoMap.keySet())
			{
				tempbookmarkNamestr = bookmarkName + ",";
				bookmarkNameStr = bookmarkNameStr.concat(tempbookmarkNamestr);
			}
			ind = bookmarkNameStr.lastIndexOf(",");
			bookmarkNameStr = bookmarkNameStr.substring(0, ind);
			bookmarkNameStr = bookmarkNameStr.replace("\"", "");
		}
	}

	private void getDemographicInfoStr(Map<String, String> bookmarkDemographicInfoMap)
	{
		// TODO Auto-generated method stub
		demogrphicInfoStr = "";

		if (bookmarkDemographicInfoMap != null && !bookmarkDemographicInfoMap.isEmpty())
		{
			for (String bookmarkFieldName : bookmarkDemographicInfoMap.keySet())
			{
				tempDemogrphicInfoStr = "\"" + bookmarkDemographicInfoMap.get(bookmarkFieldName) + "\",";
				demogrphicInfoStr = demogrphicInfoStr.concat(tempDemogrphicInfoStr);
			}
			ind = demogrphicInfoStr.lastIndexOf(",");
			demogrphicInfoStr = demogrphicInfoStr.substring(0, ind);
		}
	}
}
