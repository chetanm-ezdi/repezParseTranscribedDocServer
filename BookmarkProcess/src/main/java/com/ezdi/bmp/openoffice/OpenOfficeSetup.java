package com.ezdi.bmp.openoffice;

import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class OpenOfficeSetup
{
	public static OpenOfficeConnection	connection;

	public static void startConnection() throws ConnectException
	{
		connection = new SocketOpenOfficeConnection("192.168.1.69", 8100);
		connection.connect();
	}

	public static void closeConnection()
	{
		connection.disconnect();
	}

	public static DocumentConverter getConverter()
	{
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		return converter;
	}
}
