package org.yazilimkulubu.webinformation.controller;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.yazilimkulubu.webinformation.model.BulletinMember;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 9/15/13
 * Time: 6:42 PM
 */

@Stateful
@Model
public class BulletinMemberRegistration {

	@Inject
	private EntityManager entityManager;

	@Inject
	private FacesContext facesContext;

	@Inject
	private Event<BulletinMember> bulletinMemberEvent;

	private BulletinMember bulletinMember;

	@Produces
	@Named
	public BulletinMember getBulletinMember() {
		return bulletinMember;
	}
	
	public void registerBulletinMember() throws Exception{
			entityManager.persist(bulletinMember);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Kayıt işlemi başarıyla tamamlandı.","Kayıt işlemi başarıyla tamamlandı."));
			bulletinMemberEvent.fire(bulletinMember);
			initBulletinMemeber();
			//facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kayıtlı e-mail adresi!","Kayıtlı e-mail adresi!"));
	}

	@PostConstruct
	public void initBulletinMemeber() {
		bulletinMember = new BulletinMember();
	}

}
