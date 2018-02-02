package com.crm.utils;

import org.apache.log4j.Logger;

public class Log {
	
	private static Logger log = Logger.getLogger(Log.class.getName()); 
	
	public static void startTestCase(String sTestCaseName){
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+"------S-T-A-R-T-S-"+"      $$$$$$$$$$$$$$$$$$$$$$$$$");
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
	}
	
	public static void endTestCase(String sTestCaseName){
		log.info("X");
		log.info("X");
		log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+sTestCaseName+"-----E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
		log.info("X");
		log.info("X");
	}
	
	public static void logInfo(String message){
		log.info(message);
	}
	
	public static void logWarning(String message){
			log.warn(message);
		}
	
	public static void logDebug(String message){
		log.debug(message);
	}
	
	public static void logFatal(String message){
		log.fatal(message);
	}
	
	public static void logError(String message){
	log.error(message);	
	}


}
