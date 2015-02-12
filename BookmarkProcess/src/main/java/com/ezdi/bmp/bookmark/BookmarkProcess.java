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

	private static Map<String, String>	bookmarkMap;

	private static String				bookmarkName;
	private static String				bookmarkValue;
	private static String				bookmarkNameStr;
	private static String				tempBookmarkNamestr;
	private static String				bookmarkValStr;
	private static String				tempBookmarkValStr;
	private static String				docFileName;
	private static FileInputStream		fileInputStream;

	private static int					ind;

	public BookmarkProcess() throws Exception
	{
		BookMarkUtils.getBookmarkMapping();
		queryMySqlObj = new QueryMySql();
	}

	public void getBookmarkInfo(File docFile, String dirPath) throws IOException
	{
		// TODO Auto-generated method stub
		docFileName = docFile.getName();
		fileInputStream = new FileInputStream(docFile);
		getBookmarkFromFiles(fileInputStream);
		getBookmarkNameStr(bookmarkMap);
		getBookmarkValStr(bookmarkMap);
		queryMySqlObj.insertEntryIntoBookmarkTable(docFileName, dirPath, bookmarkNameStr, bookmarkValStr);

	}

	private void getBookmarkFromFiles(FileInputStream fileInputStream) throws IOException
	{
		// TODO Auto-generated method stub
		bookmarkMap = new LinkedHashMap<String, String>();

		document = new HWPFDocument(fileInputStream);
		Bookmarks bookmarks = document.getBookmarks();

		for (int k = 0; k < bookmarks.getBookmarksCount(); k++)
		{
			bookmark = bookmarks.getBookmark(k);
			bookmarkName = bookmark.getName().trim();

			if (BookMarkUtils.consideredBookmarkMap.containsKey(bookmarkName))
			{
				bookmarkValue = document.getText().subSequence(bookmark.getStart(), bookmark.getEnd()).toString().trim();
				bookmarkMap.put(BookMarkUtils.consideredBookmarkMap.get(bookmarkName), bookmarkValue);
			}
		}

	}

	private void getBookmarkNameStr(Map<String, String> bookmarkMap)
	{
		// TODO Auto-generated method stub
		bookmarkNameStr = "";

		if (bookmarkMap != null && !bookmarkMap.isEmpty())
		{
			for (String bookmarkName : bookmarkMap.keySet())
			{
				tempBookmarkNamestr = bookmarkName + ",";
				bookmarkNameStr = bookmarkNameStr.concat(tempBookmarkNamestr);
			}
			ind = bookmarkNameStr.lastIndexOf(",");
			bookmarkNameStr = bookmarkNameStr.substring(0, ind);
			bookmarkNameStr = bookmarkNameStr.replace("\"", "");
		}
	}

	private void getBookmarkValStr(Map<String, String> bookmarkMap)
	{
		// TODO Auto-generated method stub
		bookmarkValStr = "";

		if (bookmarkMap != null && !bookmarkMap.isEmpty())
		{
			for (String bookmarkName : bookmarkMap.keySet())
			{
				tempBookmarkValStr = "\"" + bookmarkMap.get(bookmarkName) + "\",";
				bookmarkValStr = bookmarkValStr.concat(tempBookmarkValStr);
			}
			ind = bookmarkValStr.lastIndexOf(",");
			bookmarkValStr = bookmarkValStr.substring(0, ind);
		}
	}
}
