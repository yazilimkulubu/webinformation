package org.yazilimkulubu.webinformation.controller.faculty;

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

import org.yazilimkulubu.webinformation.model.Faculty;


/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 13/10/13
 * Time: 18:49
 */

@Stateful
@Model
public class FacultyRegistrationBean {
	
	@Inject
	private EntityManager entityManager;

	@Inject
	private FacesContext facesContext;

	@Inject
	private Event<Faculty> facultyEvent;
	
	private Faculty faculty;
	
	@Named
	@Produces
	public Faculty getFaculty() {
		return faculty;
	}
	
	public void registerFaculty(){
		try {
			entityManager.persist(faculty);
			facultyEvent.fire(faculty);			
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fakülte kaydı başarıyla yapıldı.",faculty.getName()+" başarıyla kaydedildi."));
			initFaculty();
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().contains("unique")){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Kayıtlı fakülte!","Daha önce kaydedilmiş fakülte adı girildi!"));
			}
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fakülte kaydedilemedi!","Fakülte kaydedilemedi!"));
		}
		
	}
	
	@PostConstruct
	public void initFaculty(){
		faculty = new Faculty();
	}
	
	

}
