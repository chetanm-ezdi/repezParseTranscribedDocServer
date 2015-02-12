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

public class Test {

	private static Message msg;
	private static String fileName;
	private static String tenantCode;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		init();
		communication();
		release();
	}
	
	private static void init() throws IOException
	{
		// TODO Auto-generated method stub
		OpenOfficeSetup.startConnection();
		ProducerConumerSetup.rabbitmqQueueSetup();
		ProducerConumerSetup.startConsuming(Constant.NLP_QUEUE);
		
	}
	
	private static void communication() throws ShutdownSignalException, ConsumerCancelledException, IOException, InterruptedException, ClassNotFoundException {
		// TODO Auto-generated method stub
		 MessageProcess msgProcessObj=new MessageProcess();
		 while(true){
			 msg=msgProcessObj.recieveMessage();
			 fileName=msg.getFileName();
			 tenantCode=msg.getTenantCode();
		     File textFile=FileConversion.convertIntoText(fileName,tenantCode);
		     fileName=textFile.getName();
		     msg.setFileName(fileName);
			 msg.setTenantCode(textFile.getParentFile().getName());
		     msgProcessObj.sendMessage(msg);
		 }
	}

	private static void release() throws IOException
	{
		// TODO Auto-generated method stub
		OpenOfficeSetup.closeConnection();
		ProducerConumerSetup.shutDownProcess();
	}

}
