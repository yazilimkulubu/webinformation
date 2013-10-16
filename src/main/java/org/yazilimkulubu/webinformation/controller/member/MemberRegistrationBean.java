package org.yazilimkulubu.webinformation.controller.member;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.yazilimkulubu.webinformation.model.Member;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 13/10/13
 * Time: 18:49
 */

@Stateful
@Model
public class MemberRegistrationBean {
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private Event<Member> memberEvent;
	
	private Member member;
	
	@Named
	@Produces
	public Member getMember() {
		return member;
	}



	@PostConstruct
	public void initMember(){
		member = new Member();
	}
	
	
}
