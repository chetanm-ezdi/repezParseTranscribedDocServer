package com.ezdi.bmp.fileconversion;

import java.io.File;

import com.ezdi.bmp.openoffice.OpenOfficeSetup;
import com.ezdi.bmp.utils.Constant;

public class FileConversion
{
	public static File		docFile;
	private static File		inputFile;
	private static String	docFileName;
	private static String	fileExtension;

	public void convertIntoDoc(String inputFileName, String dirPath)
	{
		// TODO Auto-generated method stub
		inputFile = new File(Constant.ROOT + dirPath + "/" + inputFileName);
		fileExtension = inputFileName.substring(inputFileName.lastIndexOf(".") + 1, inputFileName.length());
		docFileName = inputFileName.replace(fileExtension, Constant.DOC_FILE_EXTENSION);
		docFile = new File(Constant.ROOT + "/" + Constant.DOC_FILES_FOLDER_NAME + "/" + docFileName);
		OpenOfficeSetup.getConverter().convert(inputFile, docFile);
	}
}
