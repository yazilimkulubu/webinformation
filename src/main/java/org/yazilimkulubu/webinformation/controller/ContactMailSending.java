package org.yazilimkulubu.webinformation.controller;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.yazilimkulubu.webinformation.model.ContactMail;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 17/09/13
 * Time: 14:04
 */

@Model
@Stateful
public class ContactMailSending {
	
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
		contactMailSendingAsyncBean.contactEmailAsyncSend(httpServletRequest.getRemoteAddr(),contactMail.getNameSurname(),contactMail.getFromAdress(),contactMail.getText());
	}
	
	@PostConstruct
	public void initContactMail() {
			contactMail = new ContactMail();
	}
}
