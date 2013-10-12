package org.yazilimkulubu.webinformation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created with JBoss Developer Studio 6.0
 * User: Batuhan ÇIKRIKCI
 * Date: 12/10/13
 * Time: 16:35
 */

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames="name"))
public class Faculty implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message="Fakülte adı boş geçilemez!")
	private String name;
	
	@OneToOne
	private Depertmant depertmant;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Depertmant getDepertmant() {
		return depertmant;
	}

	public void setDepertmant(Depertmant depertmant) {
		this.depertmant = depertmant;
	}

}
