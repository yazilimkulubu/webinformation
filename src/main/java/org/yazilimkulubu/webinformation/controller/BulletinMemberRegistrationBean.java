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
		boolean j = false;
		try {
			for (int i = 0; i < bulletinMemberProducer.getBulletinMembers().size(); i++) {
				if (bulletinMemberProducer.getBulletinMembers().get(i).getEmail().equals(bulletinMember.getEmail())) {
					j = true;
					break;
				}
			}
			if(j == true){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kayıtlı e-mail adresi!","Kayıtlı e-mail adresi!"));
			}else {
				try {
					System.out.println(bulletinMemberProducer.getBulletinMembers().get(0).getEmail());
				} catch (Exception e) {
					// TODO: handle exception
				}
						
				try {
					entityManager.persist(bulletinMember);
					facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Kayıt işlemi başarıyla tamamlandı.","Kayıt işlemi başarıyla tamamlandı."));
					bulletinMemberEvent.fire(bulletinMember);
					initBulletinMember();
				} catch(Throwable throwable) {
					throwable.printStackTrace();
					entityManager.close();
					facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kayıtlı e-mail adresi!","Kayıtlı e-mail adresi!"));
				}
			}
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kayıt yapılamadı!","Kayıt yapılamadı"));
		}
		
	}
	
	@PostConstruct
	public void initBulletinMember() {
			bulletinMember = new BulletinMember();
	}
}
