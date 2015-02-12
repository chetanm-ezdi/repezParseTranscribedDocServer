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
		Test test = new Test();
		test.initialize();
		FileUtils fileUtilObj = new FileUtils();
		fileUtilObj.getInputFolder();
		test.release();
	}

	private void initialize() throws IOException
	{
		// TODO Auto-generated method stub
		OpenOfficeSetup.startConnection();
		ProducerConumerSetup.rabbitmqQueueSetup();
		ProducerConumerSetup.declareQueue(Constant.DOC_TO_TEXT_QUEUE);
	}

	private void release() throws IOException
	{
		// TODO Auto-generated method stub
		OpenOfficeSetup.closeConnection();
		ProducerConumerSetup.shutDownProcess();
	}

}
