package com.ezdi.bmp.test;

import java.io.IOException;

import com.ezdi.bmp.openoffice.OpenOfficeSetup;
import com.ezdi.bmp.rabbitmq.ProducerConumerSetup;
import com.ezdi.bmp.utils.Constant;
import com.ezdi.bmp.utils.FileUtils;

public class Test
{

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub

		initProcess();
		FileUtils fileUtilObj = new FileUtils();
		fileUtilObj.getInputFolder();
		releaseProcess();
	}

	private static void initProcess() throws IOException
	{
		// TODO Auto-generated method stub
		OpenOfficeSetup.startConnection();
		ProducerConumerSetup.rabbitmqQueueSetup();
		ProducerConumerSetup.declareQueue(Constant.DOC_TO_TEXT_QUEUE);
	}

	private static void releaseProcess() throws IOException
	{
		// TODO Auto-generated method stub
		OpenOfficeSetup.closeConnection();
		ProducerConumerSetup.shutDownProcess();
	}

}
