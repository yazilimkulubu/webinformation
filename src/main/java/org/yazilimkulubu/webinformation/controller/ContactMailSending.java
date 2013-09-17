package org.yazilimkulubu.webinformation.controller;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.mail.SimpleEmail;
import org.yazilimkulubu.webinformation.model.ContactMail;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 17/09/13
 * Time: 14:04
 */
@Stateful
@Model
public class ContactMailSending {
	
	@Inject
	private FacesContext facesContext;
		
	private ContactMail contactMail;
	
	@Produces
	@Named
	public ContactMail getContactMail() {
		return contactMail;
	}
	
	SimpleEmail simpleEmail = new SimpleEmail();

	@SuppressWarnings("deprecation")
	public void contactEmailSend(){
		
		simpleEmail.setHostName("smtp.gmail.com");
		simpleEmail.setSmtpPort(465);
		simpleEmail.setSSL(true);
		simpleEmail.setAuthentication("contact.yazilim.kulubu@gmail.com", "***********");
		try {
			simpleEmail.addTo("contact@yazilimkulubu.org");
			simpleEmail.setFrom(contactMail.getFromAdress(),contactMail.getNameSurname());
			simpleEmail.setSubject("Web Information Contact Page");
			simpleEmail.setMsg(contactMail.getText());
			simpleEmail.addCc(contactMail.getFromAdress());
			simpleEmail.send();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"İletiniz başarıyla gönderildi.","İletiniz başarıyla gönderildi."));
			
			System.out.println(contactMail.getFromAdress());
			System.out.println(contactMail.getNameSurname());
			System.out.println(contactMail.getText());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "İletiniz gönderilemedi!","İletiniz gönderilemedi!"));
		}
	}
	
	@PostConstruct
	public void initContactMail() {
			contactMail = new ContactMail();
	}

}
