package org.yazilimkulubu.webinformation.controller.mail;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import org.apache.commons.mail.SimpleEmail;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 14/09/13
 * Time: 15:22
 */

@Stateless
public class ContactMailSendingAsyncBean {
	
	private String mailTo;
	private String mailFrom;
	private String mailFromIp;
	private String subject;
	private String text;
	private String nameSurname;
		
	public String getMailTo() {
		return mailTo;
	}
	
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
	public String getMailFrom() {
		return mailFrom;
	}
	
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	
	public String getMailFromIp() {
		return mailFromIp;
	}
	public void setMailFromIp(String mailFromIp) {
		this.mailFromIp = mailFromIp;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}
	
	@SuppressWarnings("deprecation")
	@Asynchronous
	public void contactEmailAsyncSend(){
		
		SimpleEmail simpleEmail = new SimpleEmail();
		
		try {
				simpleEmail.setHostName("smtp.gmail.com");
				simpleEmail.setSmtpPort(465);
				simpleEmail.setSSL(true);
				simpleEmail.setAuthentication("contact.yazilim.kulubu@gmail.com", "yazilim23kulubu");
				
				simpleEmail.addTo(mailTo);
				simpleEmail.setFrom(mailFrom,nameSurname+" ("+mailFromIp+")");
				simpleEmail.setSubject(subject);
				simpleEmail.setMsg(text);
				simpleEmail.addCc(mailFrom);
				simpleEmail.setCharset("UTF-8");
				simpleEmail.send();
				simpleEmail = null;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
