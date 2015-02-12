package com.ezdi.bmp.utils;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;

import com.ezdi.bmp.bookmark.BookmarkProcess;
import com.ezdi.bmp.fileconversion.FileConversion;
import com.ezdi.bmp.rabbitmq.MessageProcess;
import com.ezdi.server.common.component.Message;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class FileUtils
{

	private static BookmarkProcess	bookmarkProcessObj;
	private static MessageProcess	msgProcessObj;

	private static Message			msg;

	private static String			dirPath;
	private static String			fileName;

	private static int				fileCount	= 0;

	private static File				docFile;

	public FileUtils() throws Exception
	{
		// TODO Auto-generated method stub
		bookmarkProcessObj = new BookmarkProcess();
		msgProcessObj = new MessageProcess();
	}

	public void getInputFolder() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException
	{
		// TODO Auto-generated method stub
		File dir = new File(Constant.INPUT_FILES_FOLDER);
		while (true)
		{
			getFiles(dir);
		}
		//System.out.println("Total doc files are :" + fileCount);
		//System.out.println("Complete...");
	}

	public void getFiles(File dir) throws ConnectException, ShutdownSignalException, ConsumerCancelledException, InterruptedException
	{
		// TODO Auto-generated method stub
		try
		{
			File[] files = dir.listFiles();
			for (File file : files)
			{
				if (file.isDirectory())
				{
					getFiles(file);
				}
				else
				{
					fileCount++;
					//System.out.println("file converting is :" + fileCount + file.getName());
					docFile = FileConversion.convertIntoDoc(file);
					dirPath = findCurrentDirPathFromInputDir(dir);
					file.delete();
					bookmarkProcessObj.getDemographicInfoFromBookmarks(docFile, dirPath);
					fileName = docFile.getName();
					msg = new Message();
					msg.setFileName(fileName);
					msg.setTenantCode(docFile.getParentFile().getName());
					msgProcessObj.sendMessage(msg);
				}
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public String findCurrentDirPathFromInputDir(File dir)
	{

		String dirPath = dir.getAbsolutePath();
		dirPath = dirPath.substring(Constant.INPUT_FILES_FOLDER.length());
		if (dirPath.length() > 0)
		{
			return dirPath;
		}
		else
		{
			return "ROOT";
		}

		// TODO Auto-generated method stub
	}

}
