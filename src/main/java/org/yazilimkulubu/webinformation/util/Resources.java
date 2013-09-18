package org.yazilimkulubu.webinformation.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 9/15/13
 * Time: 19:25
 */

public class Resources {

	@Produces
	@PersistenceContext
	private EntityManager entityManager;

	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
			return FacesContext.getCurrentInstance();
			
	}
	
	@Produces
	@RequestScoped
	public HttpServletRequest httpServletRequest(){
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
	}
	
}
