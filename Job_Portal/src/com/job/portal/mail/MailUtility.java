package com.job.portal.mail;

/**
*
* @author Prashant
*/
import java.io.File;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtility {

	private String to;
	private String subject;
	private String text;

	public MailUtility(String to, String subject, String text){
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public boolean send() {
		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			Properties props=new Properties();
			props.setProperty("mail.transport.protocol","smtp");
			props.setProperty("mail.host","smtp.gmail.com");
			props.put("mail.smtp.auth","true");
			props.put("mail.smtp.port","465");
			props.put("mail.debug","true");
			props.put("mail.smtp.socketFactory.port","465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback","false");
			Session session=Session.getDefaultInstance(props,new GJMailAuthenticator());
			session.setDebug(true);
			Transport transport=session.getTransport();
			InternetAddress addressFrom=new InternetAddress("xxxx@gmail.com");
			MimeMessage message=new MimeMessage(session);
			message.setSender(addressFrom);
			message.setSubject(subject);
			message.setContent(text,"text/html");			
			InternetAddress addressTo=new InternetAddress(to);
			message.setRecipient(Message.RecipientType.TO,addressTo);
			transport.connect();
			Transport.send(message);
			transport.close();
			System.out.println("DONE");

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean sendWithAttachment(File attachFile) {
		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			Properties props=new Properties();
			props.setProperty("mail.transport.protocol","smtp");
			props.setProperty("mail.host","smtp.gmail.com");
			props.put("mail.smtp.auth","true");
			props.put("mail.smtp.port","465");
			props.put("mail.debug","true");
			props.put("mail.smtp.socketFactory.port","465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback","false");
			Session session=Session.getDefaultInstance(props,new GJMailAuthenticator());
			session.setDebug(true);
			Transport transport=session.getTransport();
			InternetAddress addressFrom=new InternetAddress("xxxx@gmail.com");
			MimeMessage message=new MimeMessage(session);
			message.setSender(addressFrom);
			message.setSubject(subject);
			 // create the message part 
		    MimeBodyPart messageBodyPart = new MimeBodyPart();
		    MimeBodyPart messageAttachmentPart = new MimeBodyPart();
		    //fill message
		    messageBodyPart.setContent(text, "text/html");
		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
		    // Part two is attachment
		    messageAttachmentPart = new MimeBodyPart();
		    DataSource source = new FileDataSource(attachFile.getAbsolutePath());
		    messageAttachmentPart.setDataHandler(new DataHandler(source));
		    messageAttachmentPart.setFileName(attachFile.getName());
		    multipart.addBodyPart(messageAttachmentPart);
		    // Put parts in message
		    message.setContent(multipart);			
			InternetAddress addressTo=new InternetAddress(to);
			message.setRecipient(Message.RecipientType.TO,addressTo);
			transport.connect();
			Transport.send(message);
			transport.close();
			System.out.println("DONE");

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
class GJMailAuthenticator extends javax.mail.Authenticator{
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication("xxx@gmail.com","xxx");

	}
}
