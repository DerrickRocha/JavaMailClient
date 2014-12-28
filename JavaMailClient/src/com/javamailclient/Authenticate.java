package com.javamailclient;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * This class is used to get password authentication..
 *
 * @author Derrick Rocha
 */
public class Authenticate extends Authenticator{
	
	private PasswordAuthentication authentication;
	
public Authenticate(String username,String password){
		
	 authentication = new PasswordAuthentication(username,password);
	 
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		
		return authentication;
	}

	

}
