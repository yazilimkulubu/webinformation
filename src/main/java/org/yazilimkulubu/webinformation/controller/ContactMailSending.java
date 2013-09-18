package org.yazilimkulubu.webinformation.controller;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 17/09/13
 * Time: 14:04
 */

@Model
@Stateful
public class ContactMailSending {
	
	@Inject
	private HttpServletRequest httpServletRequest;
	
	@Inject
	private ContactMailSendingAsyncBean contactMailSendingAsyncBean;
	
	public void contactEmailSend(){
		contactMailSendingAsyncBean.contactEmailAsyncSend(httpServletRequest.getRemoteAddr());
	}
	
}
