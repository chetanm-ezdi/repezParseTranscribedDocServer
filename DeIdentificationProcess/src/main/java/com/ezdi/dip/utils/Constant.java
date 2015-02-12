package com.ezdi.dip.utils;

public class Constant
{

	/**
	 * File Constants.
	 */
	public static final String	INPUT_FILES_FOLDER		= "/home/likewise-open/EZDI-DOMAIN/cmoradiya/workspace/ParseTranscribedDocsProject_ Workspace/TranscribedDocParsing/input_folder";
	public static final String	TXT_FILES_FOLDER_NAME	= "text_files_folder";
	public static final String	DOC_FILES_FOLDER		= "/home/likewise-open/EZDI-DOMAIN/cmoradiya/workspace/ParseTranscribedDocsProject_ Workspace/TranscribedDocParsing/doc_files_folder";
	public static final String	ROOT					= "";
	public static final String	DEIDENTIFIEDFILES		= "";

	/**
	 * File extensions Constants.
	 */

	public static final String	DOC_FILE_EXTENSION		= "doc";
	public static final String	TXT_FILE_EXTENSION		= "txt";

	/**
	 * Constants for mysql.
	 */

	public static final String	MYSQL_URL				= "jdbc:mysql://localhost:3306/";
	public static final String	MYSQL_DRIVER_CLASS_NAME	= "com.mysql.jdbc.Driver";
	public static final String	MYSQL_USERNAME			= "root";
	public static final String	MYSQL_PASSWORD			= "P@ssw0rd@123";

	/**
	 * Table Names
	 */
	public static final String	BOOKMARK_INFO_TABLE		= "BOOKMARK_PROCESS_SCHEMA.BookmarkInfo";

	/**
	 * Rabbitmq Queue Constant
	 */

	public static final String	DEIDENTIFICATION_QUEUE	= "deidentification_queue";

	/**
	 * Other Constant
	 */

	public static final String	HOSPITAL_ID				= "text_files_folder";
	public static final String	REPLACECHAR				= "$";
}
