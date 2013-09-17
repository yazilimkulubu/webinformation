package org.yazilimkulubu.webinformation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created with JBoss Developer Studio 6.0
 * User: Batuhan ÇIKRIKCI
 * Date: 15/09/13
 * Time: 18:08
 */

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames="email"))
public class BulletinMember implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull(message="e-mail adresi boş olamaz!")
	@NotEmpty(message="e-mail adresi boş olamaz!")
	@Email(message="Lütfen geçerli bir e-mail adresi girin.")
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
			return email;
	}

	public void setEmail(String email) {
			this.email = email;
	}
	
}
