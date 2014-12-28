package com.javamailclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.swing.*;

import com.emailutils.EmailUtilities;
/**
 * This class contains the Gui for the user and the 
 * action listener implementation for the buttons.
 *
 * @author Derrick Rocha
 */
public class MailClientGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JTextField toField;
	private JLabel untitled_4;
	private JLabel untitled_5;
	private JLabel untitled_7;
	private JLabel untitled_8;
	private JTextField fromField;
	private JTextField ccField;
	private JTextArea messageArea;
	private JScrollPane scrollPane;
	private JButton sendButton;
	private JLabel untitled_16;
	private JTextField bcField;
	private JLabel untitled_18;     
	private JTextField subjectField;
	private JButton clearButton;
	private JButton exitButton;
	private JButton attachmentButton;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem exitItem;
	private JMenuItem settingsItem;

	private JavaMailClient client;
	private Email email;

	

	public MailClientGui(){


		setupGUI();
		loadSettings();

	}



	private void setupGUI(){


		setLayout(null);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);

		settingsItem = new JMenuItem("Settings");
		settingsItem.addActionListener(this);

		menu = new JMenu("File");
		menu.add(settingsItem);
		menu.add(exitItem);

		menuBar = new JMenuBar();
		menuBar.add(menu);

		setJMenuBar(menuBar);

		toField = new JTextField();
		toField.setLocation(66,44);
		toField.setSize(315,25);
		toField.setColumns(5);
		add(toField);

		untitled_4 = new JLabel();
		untitled_4.setLocation(7,12);
		untitled_4.setSize(45,25);
		untitled_4.setText("From:");
		add(untitled_4);

		untitled_5 = new JLabel();
		untitled_5.setLocation(6,44);
		untitled_5.setSize(27,25);
		untitled_5.setText("To:");
		add(untitled_5);

		untitled_7 = new JLabel();
		untitled_7.setLocation(6,77);
		untitled_7.setSize(26,25);
		untitled_7.setText("Cc:");
		add(untitled_7);

		untitled_8 = new JLabel();
		untitled_8.setLocation(4,172);
		untitled_8.setSize(72,26);
		untitled_8.setText("Message:");
		add(untitled_8);

		fromField = new JTextField();
		fromField.setLocation(66,11);
		fromField.setSize(315,25);
		fromField.setColumns(10);
		fromField.setEditable(false);
		add(fromField);

		ccField = new JTextField();
		ccField.setLocation(66,78);
		ccField.setSize(316,25);
		ccField.setColumns(10);
		add(ccField);

		messageArea = new JTextArea();
		messageArea.setLocation(5,198);
		messageArea.setSize(377,208);

		scrollPane = new JScrollPane(messageArea);
		scrollPane.setLocation(5,198);
		scrollPane.setSize(377,208);
		scrollPane.setAutoscrolls(true);
		add(scrollPane);

		sendButton = new JButton();
		sendButton.setLocation(6,436);
		sendButton.setSize(88,31);
		sendButton.setText("Send");
		sendButton.addActionListener(this);
		add(sendButton);

		untitled_16 = new JLabel();
		untitled_16.setLocation(5,110);
		untitled_16.setSize(25,25);
		untitled_16.setText("Bc:");
		add(untitled_16);

		bcField = new JTextField();
		bcField.setLocation(66,111);
		bcField.setSize(316,25);
		bcField.setText("");
		bcField.setColumns(10);
		add(bcField);

		untitled_18 = new JLabel();
		untitled_18.setLocation(4,142);
		untitled_18.setSize(61,25);
		untitled_18.setText("Subject:");
		add(untitled_18);

		subjectField = new JTextField();
		subjectField.setLocation(66,142);
		subjectField.setSize(317,25);
		subjectField.setText("");
		subjectField.setColumns(10);
		add(subjectField);

		clearButton = new JButton();
		clearButton.setLocation(101,436);
		clearButton.setSize(88,30);
		clearButton.setText("Clear");
		clearButton.addActionListener(this);
		add(clearButton);

		exitButton = new JButton();
		exitButton.setLocation(294,435);
		exitButton.setSize(88,31);
		exitButton.setText("Exit");
		exitButton.addActionListener(this);
		add(exitButton);

		attachmentButton = new JButton();
		attachmentButton.setLocation(197,435);
		attachmentButton.setSize(89,30);
		attachmentButton.setText("Attach");
		add(attachmentButton);

		setTitle("My Email Client");
		setSize(390,513);
		setVisible(true);
		setResizable(true);


	}



	private void loadSettings() {


		try {

			BufferedReader in
			= new BufferedReader(new FileReader("settings.config"));
			String[] settings = in.readLine().split(":");// retrieves domain name and port number for server.
			in.close();
			
			client = new JavaMailClient();
			client.setProperties(settings[0], settings[1]);
			String  user = JOptionPane.showInputDialog(this,"Please enter you email username.").trim();
			String  pass = JOptionPane.showInputDialog(this,"Please enter your password.").trim();

			fromField.setText("<"+user+">");
			
			client.loadSession(user, pass);

		} 

		catch (FileNotFoundException e) {

			changeSettings();

		} 

		catch (IOException e) {

			e.printStackTrace();
		}

	}



	private void changeSettings(){

		
		String  domain = JOptionPane.showInputDialog(this,"Enter the domain name for the SMTP server.").trim();
		String  portNum = JOptionPane.showInputDialog(this,"Enter the port for the SMTP server.").trim();

		client = new JavaMailClient();
		client.setProperties(domain, portNum);

		String  user = JOptionPane.showInputDialog(this,"Please enter you email username.").trim();
		String  pass = JOptionPane.showInputDialog(this,"Please enter your password.").trim();

		client.loadSession(user, pass);

		EmailUtilities.writeSettings(domain, portNum);
		
		fromField.setText("<"+user+">");

	}



	@Override
	public void actionPerformed(ActionEvent event) {


		if(event.getSource()==sendButton)

			sendButtonActionPerformed();


		else if(event.getSource()==exitButton)

			System.exit(0);
		
		
		else if(event.getSource()==clearButton)
			
			clearFields();

		//menu items ///////////////////////////////////////////////////////////////

		else if(event.getSource()==exitItem)

			System.exit(0);


		else if(event.getSource()==settingsItem)

			changeSettings();
       // end of menu items ////////////////////////////////////////////////////////


	}



	private void clearFields() {
		
		
		toField.setText("");
		ccField.setText("");
		bcField.setText("");
		subjectField.setText("");
		messageArea.setText("");
		
	}



	private void sendButtonActionPerformed() {

		
		email = new Email(fromField.getText(),
					toField.getText(),ccField.getText(),bcField.getText(),
					subjectField.getText(),messageArea.getText());
		
		
		if(isFieldsValid()){
			
			sendMail();// sends mail to the server.
			
		}
		
	}



	private void sendMail() {
		
	
		try {

			client.sendEmail(email);

		} 
		// lets user enter correct password and user name if login fails.
		catch(AuthenticationFailedException e){
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error logging in.");
			
			String  user = JOptionPane.showInputDialog(this,"Please enter you email username.").trim();
			String  pass = JOptionPane.showInputDialog(this,"Please enter your password.").trim();

			client.loadSession(user, pass);
			
			fromField.setText("<"+user+">");
			
			
		}
		//Thrown if a recipient field is not formatted correctly.  
		//Tells user to enter  correct recipient(s) in "To" field.
		catch(SendFailedException e){
		
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Must enter a valid recepient!");
	
		}
	
		catch (MessagingException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "error in typing");
	
		}
		
	}



	private boolean isFieldsValid() {
		
		
		if(!EmailUtilities.isValid(email.getFrom())){
			// if "From" field is not in correct format, tell the user to enter correct format in "From" field.
			JOptionPane.showMessageDialog(this, "Error in From field. Must enter correct format");
			String  user = JOptionPane.showInputDialog(this,"Please enter you email username.").trim();
			String  pass = JOptionPane.showInputDialog(this,"Please enter your password.").trim();

			client.loadSession(user, pass);
			
			fromField.setText("<"+user+">");
			// show red 
			return false;
		}
		
		// if "To" field is not in correct format, tell the user to enter correct format in "To" field.
		else if(!EmailUtilities.isValid(email.getTo())){
			
			JOptionPane.showMessageDialog(this, "Error in To field. Must enter correct format");
			// show red 
			return false;
		} 
		
		else if(!email.getCc().contentEquals("")&&!EmailUtilities.isValid(email.getCc())){
			
			JOptionPane.showMessageDialog(this, "Error in Cc field. Must enter correct format");
			// show red 
			return false;
		}
		
		else if(!email.getBcc().contentEquals("")&&!EmailUtilities.isValid(email.getBcc())){
			
			JOptionPane.showMessageDialog(this, "Error in Bcc field. Must enter correct format");
			// show red 
			return false;
		}
		
		
		else
			
			return true;
		
	}



	public static void main(String[]args){

		new MailClientGui();
	}


}  