package com.javamailclient;

import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class represents an email client that
 * sends email's using the JavaMail API.
 *
 * @author Derrick Rocha
 */
public class JavaMailClient {
	
	private Properties properties;
	private Session session;
	private Message message;
	private Email email;
	
	
	public JavaMailClient(String emailServerName
			,String username,String password, String port){
	
		
	}
	

	public JavaMailClient() {
		
	}


	/**
	 * Sets the Properties object with 
	 * the provided SMTP server and port.
	 * 
	 * @param emailServerName The domain name to the SMTP server.
	 * @param port The port number to the SMTP server.
	 */
	public void setProperties(String emailServerName,String port) {
		
		properties = new Properties();
		properties.put("mail.smtp.host", emailServerName);
    	properties.put("mail.smtp.socketFactory.port", port);
    	properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    	properties.put("mail.smtp.auth", "true");
    	properties.put("mail.smtp.port", port);
		
	}
	
	
	/**
	 * Starts a Session with the provided user name and password.
	 * 
	 * @param username The login name to your email account.
	 * @param password The password to your email account.
	 */
	public void loadSession(String username, String password) {
		
		session = Session.getDefaultInstance(properties,
				new Authenticate(username,password));
		
	}

	
	/**
	 * Prepares the Message object.
	 * 
	 * @param mail Email object to send to SMTP server.
	 */
	private void loadMessage(Email mail) throws AddressException, MessagingException {
		
		message = new MimeMessage(session);
		email = mail;
		setFrom();
		setTo();
		
		if(!mail.getCc().contentEquals(""))
			setCc();
		
		if(!mail.getBcc().contentEquals(""))
			setBcc();
		
		setSubject();
	    setMessage();
	    
	

	}
	
	private void setBcc() throws AddressException, MessagingException {
		
		message.setRecipient(Message.RecipientType.BCC,
				new InternetAddress(email.getBcc()));
		
	}


	private void setCc() throws AddressException, MessagingException {
		
		message.setRecipient(Message.RecipientType.CC,
				new InternetAddress(email.getCc()));
		
	}


	private void setMessage() throws MessagingException {
		
		message.setText(email.getMessage());
		
	}


	private void setSubject() throws MessagingException {
		
		message.setSubject(email.getSubject());
		
	}


	private void setTo() throws MessagingException {
		
		message.setRecipient(Message.RecipientType.TO,
				new InternetAddress(email.getTo()));
	}


	private void setFrom() throws MessagingException {
		
		message.setFrom(new InternetAddress(email.getFrom()));
	}


	/**
	 * Used to send email to SMTP server.
	 * 
	 * @param mail Email object to send to SMTP server.
	 */
	public void sendEmail(Email mail) throws MessagingException{
		
		loadMessage(mail);
	    Transport.send(message);
	    
	}


}
