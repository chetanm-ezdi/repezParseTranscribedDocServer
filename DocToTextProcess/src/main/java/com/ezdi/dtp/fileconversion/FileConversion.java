package com.ezdi.dtp.fileconversion;

import java.io.File;

import com.ezdi.dtp.openoffice.OpenOfficeSetup;
import com.ezdi.dtp.utils.Constant;

public class FileConversion
{
	public static File textFile;
	public static File inputFile;
	public static String textFileName;
	public static String fileExtension;
	
	public static File convertIntoText(String inputFileName, String tenantCode)
	{
		// TODO Auto-generated method stub
         
		inputFile=new File(Constant.ROOT+"/"+tenantCode+"/"+inputFileName);
		fileExtension = inputFileName.substring(inputFileName.lastIndexOf(".") + 1, inputFileName.length());
		textFileName = inputFileName.replace(fileExtension, Constant.TXT_FILE_EXTENSION);
		textFile = new File(Constant.ROOT+"/"+Constant.TXT_FILES_FOLDER_NAME + "/" + textFileName);
		OpenOfficeSetup.getConverter().convert(inputFile, textFile);
		return textFile;

	}
}
