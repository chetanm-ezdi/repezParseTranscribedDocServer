package com.ezdi.dtp.rabbitmq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ezdi.dtp.utils.Constant;
import com.ezdi.server.common.component.Message;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class MessageProcess
{

	private static Message	msg;

	public void sendMessage(Message message) throws IOException
	{
		// TODO Auto-generated method stub
		byte[] msgBytes = getBytes(message);
		ProducerConumerSetup.publishMessage(Constant.NLP_QUEUE, msgBytes);
		ProducerConumerSetup.publishMessage(Constant.DEIDENTIFICATION_QUEUE, msgBytes);
	}

	public Message recieveMessage() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		byte[] msgBytes = ProducerConumerSetup.consumeMessage();
		msg = (Message) getObject(msgBytes);
		return msg;
	}

	private Object getObject(byte[] bytes) throws IOException, ClassNotFoundException
	{
		Message obj = null;
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		ObjectInputStream o = new ObjectInputStream(b);
		obj = (Message) o.readObject();
		o.close();
		b.close();
		return obj;
	}

	private byte[] getBytes(Message msg) throws IOException
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
