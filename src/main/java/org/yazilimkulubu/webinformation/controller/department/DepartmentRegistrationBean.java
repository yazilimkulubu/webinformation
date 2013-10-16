package org.yazilimkulubu.webinformation.controller.department;

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

import org.yazilimkulubu.webinformation.data.FacultyProducer;
import org.yazilimkulubu.webinformation.model.Department;
import org.yazilimkulubu.webinformation.model.Faculty;

/**
 * Created with JBoss Developer Studio 6.0 
 * User: Batuhan ÇIKRIKCI 
 * Date: 13/10/13
 * Time: 19:30
 */

@Stateful
@Model
public class DepartmentRegistrationBean {

	@Inject
	private EntityManager entityManager;

	@Inject
	private FacesContext facesContext;

	@Inject
	private Event<Department> departmentEvent;

	@Inject
	private FacultyProducer facultyProducer;

	private Department department;

	private int selectedFacultyId;

	private Faculty selectedFaculty;

	public int getSelectedFacultyId() {
		return selectedFacultyId;
	}

	public void setSelectedFacultyId(int selectedFacultyId) {
		this.selectedFacultyId = selectedFacultyId;
	}

	public Faculty getSelectedFaculty() {
		return selectedFaculty;
	}

	public void setSelectedFaculty(Faculty selectedFaculty) {
		this.selectedFaculty = selectedFaculty;
	}

	@Named
	@Produces
	public Department getDepartment() {
		return department;
	}

	public void registerDepartment() {
		
		if(selectedFacultyId==-1){
			department.setFaculty(null);
		}else {
			for (int i = 0; i <= facultyProducer.getFaculties().size(); i++) {
				if(selectedFacultyId==facultyProducer.getFaculties().get(i).getId()){
					selectedFaculty=facultyProducer.getFaculties().get(i);
					department.setFaculty(selectedFaculty);
					break;
				}

			}
			
		}
				
		try {
			entityManager.persist(department);
			departmentEvent.fire(department);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bölüm kaydı başarıyla yapıldı.",department.getFaculty().getName()+"ne "+department.getName()+" bölümü eklendi."));
			initDepartment();
		} catch (Exception e) {
			e.printStackTrace();
			if (selectedFacultyId==-1) {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Bir fakülte seçmelisiniz!","Bir fakülte seçmelisiniz!"));
			}
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bölüm kaydedilemedi!","Bölüm kaydedilemedi!"));
		}

	}

	@PostConstruct
	public void initDepartment() {
		department = new Department();
	}

}
