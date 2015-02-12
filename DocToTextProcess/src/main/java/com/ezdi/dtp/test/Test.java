package com.ezdi.dtp.test;

import java.io.File;
import java.io.IOException;

import com.ezdi.dtp.fileconversion.FileConversion;
import com.ezdi.dtp.openoffice.OpenOfficeSetup;
import com.ezdi.dtp.rabbitmq.MessageProcess;
import com.ezdi.dtp.rabbitmq.ProducerConumerSetup;
import com.ezdi.dtp.utils.Constant;
import com.ezdi.server.common.component.Message;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class Test
{

	private static Message	msg;
	private static String	fileName;
	private static String	tenantCode;
	private static File		textFile;

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
		OpenOfficeSetup.startConnection();
		ProducerConumerSetup.rabbitmqQueueSetup();
		ProducerConumerSetup.startConsuming(Constant.DOC_TO_TEXT_QUEUE);
		ProducerConumerSetup.declareQueue(Constant.NLP_QUEUE);
		ProducerConumerSetup.declareQueue(Constant.DEIDENTIFICATION_QUEUE);
	}

	private void communication() throws ShutdownSignalException, ConsumerCancelledException, IOException, InterruptedException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		MessageProcess msgProcessObj = new MessageProcess();
		FileConversion fileConversionObj = new FileConversion();
		while (true)
		{
			msg = msgProcessObj.recieveMessage();
			fileName = msg.getFileName();
			tenantCode = msg.getTenantCode();
			fileConversionObj.convertIntoText(fileName, tenantCode);
			textFile = FileConversion.textFile;
			fileName = textFile.getName();
			msg.setFileName(fileName);
			msg.setTenantCode(textFile.getParentFile().getName());
			msgProcessObj.sendMessage(msg);
		}
	}

	private void release() throws IOException
	{
		// TODO Auto-generated method stub
		OpenOfficeSetup.closeConnection();
		ProducerConumerSetup.shutDownProcess();
	}

}
