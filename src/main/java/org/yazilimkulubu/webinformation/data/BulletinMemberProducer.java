package org.yazilimkulubu.webinformation.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.yazilimkulubu.webinformation.model.BulletinMember;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 19/09/13
 * Time: 19:03
 */

@RequestScoped
public class BulletinMemberProducer {
	
	@Inject
	private EntityManager entityManager;
	
	private List<BulletinMember> bulletinMembers;
	
	public void onBulletinMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final BulletinMember bulletinMember) {
		retrieveAllBulletinMember();
	   }
		
	@Produces
	@Named
	public List<BulletinMember> getBulletinMembers() {
		return bulletinMembers;
	}

	@PostConstruct
	public void retrieveAllBulletinMember(){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BulletinMember> criteria = criteriaBuilder.createQuery(BulletinMember.class);
		Root<BulletinMember> bulletinMember = criteria.from(BulletinMember.class);
		criteria.select(bulletinMember);
		bulletinMembers = entityManager.createQuery(criteria).getResultList();
	}
	

}
