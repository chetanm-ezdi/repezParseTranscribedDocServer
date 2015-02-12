package com.ezdi.dip.test;

import java.io.IOException;

import com.ezdi.dip.rabbitmq.MessageProcess;
import com.ezdi.dip.rabbitmq.ProducerConumerSetup;
import com.ezdi.dip.utils.Constant;
import com.ezdi.dip.utils.FileUtils;
import com.ezdi.server.common.component.Message;

public class Test
{

	private static Message	msg;
	private static String	fileName;
	private static String	tenantCode;

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		Test test = new Test();
		test.initialize();
		test.communication();
		test.release();
	}

	private void initialize() throws IOException
	{
		// TODO Auto-generated method stub
		ProducerConumerSetup.rabbitmqQueueSetup();
		ProducerConumerSetup.startConsuming(Constant.DEIDENTIFICATION_QUEUE);
	}

	private void communication() throws Exception
	{
		// TODO Auto-generated method stub
		MessageProcess msgProcessObj = new MessageProcess();
		FileUtils fileUtilObj = new FileUtils();
		while (true)
		{
			msg = msgProcessObj.recieveMessage();
			fileName = msg.getFileName();
			tenantCode = msg.getTenantCode();
			fileUtilObj.fileProcess(fileName, tenantCode);
		}
	}

	private void release() throws IOException
	{
		// TODO Auto-generated method stub
		ProducerConumerSetup.shutDownProcess();
	}

}
