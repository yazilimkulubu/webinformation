package org.yazilimkulubu.webinformation.controller;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.mail.SimpleEmail;
import org.yazilimkulubu.webinformation.model.ContactMail;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 14/09/13
 * Time: 15:22
 */

@Stateless
public class ContactMailSendingAsyncBean {
	
	@Inject
	private FacesContext facesContext;
	
	private ContactMail contactMail;
	
	@Produces
	@Named
	public ContactMail getContactMail() {
		return contactMail;
	}
		
	@SuppressWarnings("deprecation")
	@Asynchronous
	public void contactEmailAsyncSend(String fromIp){
		
		SimpleEmail simpleEmail = new SimpleEmail();
		
		try {
				simpleEmail.setHostName("smtp.gmail.com");
				simpleEmail.setSmtpPort(465);
				simpleEmail.setSSL(true);
				simpleEmail.setAuthentication("contact.yazilim.kulubu@gmail.com", "*****");
				simpleEmail.addTo("contact@yazilimkulubu.org");
				simpleEmail.setFrom(contactMail.getFromAdress(),contactMail.getNameSurname()+" ("+fromIp+")");
				simpleEmail.setSubject("Web Information Contact Page");
				simpleEmail.setMsg(contactMail.getText());
				simpleEmail.addCc(contactMail.getFromAdress());
				simpleEmail.setCharset("UTF-8");
				simpleEmail.send();
			
		} catch (Exception e) {
			System.out.println("14");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void initContactMail() {
			contactMail = new ContactMail();
	}

}
