package org.yazilimkulubu.webinformation.controller;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import org.apache.commons.mail.SimpleEmail;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 14/09/13
 * Time: 15:22
 */

@Stateful
@LocalBean
public class ContactMailSendingAsyncBean {
		
	@SuppressWarnings("deprecation")
	@Asynchronous
	public void contactEmailAsyncSend(String fromIp, String nameSurname, String fromAdress, String text){
		
		SimpleEmail simpleEmail = new SimpleEmail();
		
		try {
				simpleEmail.setHostName("smtp.gmail.com");
				simpleEmail.setSmtpPort(465);
				simpleEmail.setSSL(true);
				simpleEmail.setAuthentication("contact.yazilim.kulubu@gmail.com", "yazilim23kulubu");
				simpleEmail.addTo("contact@yazilimkulubu.org");
				simpleEmail.setFrom(fromAdress,nameSurname+" ("+fromIp+")");
				simpleEmail.setSubject("Web Information Contact Page");
				simpleEmail.setMsg(text);
				simpleEmail.addCc(fromAdress);
				simpleEmail.setCharset("UTF-8");
				simpleEmail.send();
				simpleEmail = null;
			
		} catch (Exception e) {
			System.out.println("14");
			e.printStackTrace();
		}
		
	}	

}
