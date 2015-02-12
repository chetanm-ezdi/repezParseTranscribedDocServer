package com.ezdi.bmp.fileconversion;

import java.io.File;

import com.ezdi.bmp.openoffice.OpenOfficeSetup;
import com.ezdi.bmp.utils.Constant;

public class FileConversion
{
	public static File convertIntoDoc(File inputFile)
	{
		// TODO Auto-generated method stub
		String fileExtension;
		String docFileName;
		String inputFileName;
		File docFile;

		inputFileName = inputFile.getName();
		fileExtension = inputFileName.substring(inputFileName.lastIndexOf(".") + 1, inputFileName.length());
		docFileName = inputFileName.replace(fileExtension, Constant.DOC_FILE_EXTENSION);
		docFile = new File(Constant.DOC_FILES_FOLDER + "/" + docFileName);
		OpenOfficeSetup.getConverter().convert(inputFile, docFile);
		return docFile;
	}
}
