package com.ezdi.bmp.rabbitmq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.ezdi.bmp.utils.Constant;
import com.ezdi.server.common.component.Message;

public class MessageProcess
{

	public void sendMessage(Message message) throws IOException
	{
		// TODO Auto-generated method stub
		byte[] msgBytes = getBytes(message);
		ProducerConumerSetup.publishMessage(Constant.DOC_TO_TEXT_QUEUE, msgBytes);
	}

	private static byte[] getBytes(Message msg) throws IOException
	{

		byte[] bytes;
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(msg);
		o.flush();
		o.reset();
		bytes = b.toByteArray();
		o.close();
		b.close();

		return bytes;
	}

}
