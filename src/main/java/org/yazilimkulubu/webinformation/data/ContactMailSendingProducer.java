package org.yazilimkulubu.webinformation.data;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.yazilimkulubu.webinformation.controller.ContactMailSendingAsyncBean;
import org.yazilimkulubu.webinformation.model.ContactMail;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 17/09/13
 * Time: 14:04
 */

@Model
public class ContactMailSendingProducer {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private ContactMail contactMail;
	
	@Produces
	@Named
	public ContactMail getContactMail() {
		return contactMail;
	}

	@Inject
	private HttpServletRequest httpServletRequest;

	@Inject
	private ContactMailSendingAsyncBean contactMailSendingAsyncBean;

	public void contactEmailSend(){
		
		contactMailSendingAsyncBean.setMailFrom(contactMail.getFromAdress());
		contactMailSendingAsyncBean.setMailFromIp(httpServletRequest.getRemoteAddr());
		contactMailSendingAsyncBean.setMailTo("contact@yazilimkulubu.org");
		contactMailSendingAsyncBean.setNameSurname(contactMail.getNameSurname());
		contactMailSendingAsyncBean.setSubject("Web Information System Contact Page");
		contactMailSendingAsyncBean.setText(contactMail.getText());
		contactMailSendingAsyncBean.contactEmailAsyncSend();
		
		contactMail.setFromAdress(null);
		contactMail.setNameSurname(null);
		contactMail.setText(null);
		
	}

	@PostConstruct
	public void initContactMail() {
		contactMail = new ContactMail();
	}
}
