package org.yazilimkulubu.webinformation.controller.member;

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

import org.yazilimkulubu.webinformation.data.BulletinMemberProducer;
import org.yazilimkulubu.webinformation.model.BulletinMember;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 15/09/13
 * Time: 18:42
 */

@Stateful
@Model
public class BulletinMemberRegistrationBean {

	@Inject
	private EntityManager entityManager;

	@Inject
	private FacesContext facesContext;

	@Inject
	private Event<BulletinMember> bulletinMemberEvent;

	private BulletinMember bulletinMember;
	
	@Inject
	public BulletinMemberProducer bulletinMemberProducer;
	
	@Produces
	@Named
	public BulletinMember getBulletinMember() {
			return bulletinMember;
	}
	
	public void registerBulletinMember() {
		
		try {
			entityManager.persist(bulletinMember);
			bulletinMemberEvent.fire(bulletinMember);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Kayıt işlemi başarıyla tamamlandı.","Kayıt işlemi başarıyla tamamlandı."));
			initBulletinMember();
		} catch(Exception e) {
			e.printStackTrace();
			if(e.getMessage().contains("unique")){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Kayıtlı e-mail adresi","Daha önce kaydedilmiş e-mail adresi girildi!"));
			}
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "e-mail adresi kaydedilemedi!","e-mail adresi kaydedilemedi!"));
		}
		
	}
	
	@PostConstruct
	public void initBulletinMember() {
			bulletinMember = new BulletinMember();
	}
}
