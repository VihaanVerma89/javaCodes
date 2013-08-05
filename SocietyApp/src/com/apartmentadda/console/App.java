package com.apartmentadda.console;

import java.io.File;

public class App
{
	public static void main(String [] args)
	{
		File configFile = new File( "configFile");
		File bookingFile = new File("bookingInfo");
		
		Parser parser = new Parser();
		
		parser.readConfigFile(configFile);
		parser.processBooking(bookingFile);
	}
}
