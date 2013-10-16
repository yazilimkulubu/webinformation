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

import org.yazilimkulubu.webinformation.model.Faculty;


/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 13/10/13
 * Time: 19:22
 */

@RequestScoped
public class FacultyProducer {

	@Inject
	private EntityManager entityManager;
	
	private List<Faculty> faculties;
	
	public void onFacultyListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Faculty faculty) {
		retrieveAllFaculty();
	   }

	@Produces
	@Named
	public List<Faculty> getFaculties() {
		return faculties;
	}
	
	@PostConstruct
	public void retrieveAllFaculty(){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Faculty> criteria = criteriaBuilder.createQuery(Faculty.class);
		Root<Faculty> faculty = criteria.from(Faculty.class);
		criteria.select(faculty);
		faculties = entityManager.createQuery(criteria).getResultList();
	}
	
}
