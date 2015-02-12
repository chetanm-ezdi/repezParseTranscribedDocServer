package com.ezdi.bmp.utils;

public class Constant
{

	/**
	 * File Constants.
	 */
	public static final String	ROOT					= "";
	public static final String	DOC_FILES_FOLDER_NAME	= "";
	public static final String	INPUT_FILES_FOLDER_NAME	= "";

	/**
	 * File extensions Constants.
	 */

	public static final String	DOC_FILE_EXTENSION		= "doc";

	/**
	 * Table Names
	 */
	public static final String	BOOKMARK_INFO_TABLE		= "BOOKMARK_PROCESS_SCHEMA.BookmarkInfo";

	/**
	 * Constants for mysql.
	 */

	public static final String	MYSQL_URL				= "jdbc:mysql://localhost:3306/";
	public static final String	MYSQL_DRIVER_CLASS_NAME	= "com.mysql.jdbc.Driver";
	public static final String	MYSQL_USERNAME			= "root";
	public static final String	MYSQL_PASSWORD			= "P@ssw0rd@123";

	/**
	 * Rabbitmq Queue Constant
	 */

	public static final String	DOC_TO_TEXT_QUEUE		= "doc_to_text_queue";

}
