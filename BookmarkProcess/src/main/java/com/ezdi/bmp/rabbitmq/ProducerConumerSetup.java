package com.ezdi.bmp.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class ProducerConumerSetup
{
	private static Connection		connection;
	private static Channel			channel;
	private static QueueingConsumer	consumer;

	public static void rabbitmqQueueSetup() throws IOException
	{
		createConnection();
		createChanel();
		//startConsuming(QUEUE_NAME);
	}

	public static void shutDownProcess() throws IOException
	{
		closeChannel();
		closeConnection();
	}

	public static void createConnection() throws IOException
	{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.1.69");
		factory.setUsername("ezdi");
		factory.setPassword("P@ssw0rd@123");
		connection = factory.newConnection();
	}

	public static void createChanel() throws IOException
	{
		channel = connection.createChannel();
	}

	public static void declareQueue(String QUEUE_NAME) throws IOException
	{
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	}

	public static void publishMessage(String QUEUE_NAME, byte[] msgBytes) throws IOException
	{
		channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msgBytes);
	}

	public static void startConsuming(String QUEUE_NAME) throws IOException
	{
		consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

	}

	public static void closeChannel() throws IOException
	{
		channel.close();
	}

	public static void closeConnection() throws IOException
	{
		connection.close();
	}

	public static byte[] consumeMessage() throws ShutdownSignalException, ConsumerCancelledException, InterruptedException
	{
		QueueingConsumer.Delivery delivery = consumer.nextDelivery();
		return delivery.getBody();

	}

	public static void messageAcknowledgment(QueueingConsumer.Delivery delivery) throws IOException
	{
		channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	}

}
