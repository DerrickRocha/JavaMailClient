package com.emailutils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This class is a helper class used to write settings and check strings.
 *
 * @author Derrick Rocha
 */
public class EmailUtilities {
	
	/**This method writes the provided hostname and port number to a text file
	 * file for settings.
	 **/
	public static void writeSettings(String hostName,String port){
		
		BufferedWriter out;
		
		try {
			
			out = new BufferedWriter(new FileWriter("settings.config"));
			out.write(hostName+":"+port);
			out.close();
			
		} catch (IOException e) {
			
		}
		
		
	}
	
	/**this method makes sure the text contains a "@" symbol
	 * and has at least one charactor before and after the symbol.
	*/
	public static boolean isValid(String address){
		
		int index = address.indexOf("@");
		if(index>0&&index<(address.length()-1))
			
			return true;
		
		else
		
			return false;
		
	}

}
