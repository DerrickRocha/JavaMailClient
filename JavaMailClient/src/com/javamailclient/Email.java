package com.javamailclient;


/**
 * This class represents an email with a mail server host, a sender,
 * a receiver, a subject and a message.
 *
 * @author Derrick Rocha
 */
public class Email {
	
	
	private String from = "";
	private String to = "";
	private String cc = "";
	private String bcc = "";
	private String subject = "";
	private String message = "";
	
	
	/**
	 * Creates an email to send to the provided mail
	 * server host with the provided reciever, sender,
	 * subject and message.
	 * @param mailServerHost the mailServerHost**/
	public Email(String from,
			String to,String cc,String bc,String subject,String message){
		

		this.setFrom(from.trim());
		this.setTo(to.trim());
		this.setCc(cc.trim());
		this.setBcc(bc.trim());
		this.setSubject(subject.trim());
		this.setMessage(message.trim());
		
	}


	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}


	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}


	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}


	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}


	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}


	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}


	/**
	 * @return the bcc
	 */
	public String getBcc() {
		return bcc;
	}


	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}


	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}


	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}



}
