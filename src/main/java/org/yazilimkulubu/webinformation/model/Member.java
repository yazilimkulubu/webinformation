package org.yazilimkulubu.webinformation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created with JBoss Developer Studio 6.0
 * User: Batuhan ÇIKRIKCI
 * Date: 12/10/13
 * Time: 16:50
 */

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"email","membernumber","depertmantnumber"}))
public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message="Üye numarası boş geçilemez")
	private int memberNumber;
	
	private long depertmantNumber;
	
	private String name;
	
	private String surname;
	
	@NotEmpty(message="e-mail adresi boş olamaz!")
	@Email(message="Lütfen geçerli bir e-mail adresi girin.")
	private String email;
	
	private String password;
	
	@ManyToOne
	@JoinColumn(name="Member_FK")
	private Depertmant depertmant;
	
	private char grade;
	
	private long mobilePhone;
	
	@OneToOne
	private Role role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public long getDepertmantNumber() {
		return depertmantNumber;
	}

	public void setDepertmantNumber(long depertmantNumber) {
		this.depertmantNumber = depertmantNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Depertmant getDepertmant() {
		return depertmant;
	}

	public void setDepertmant(Depertmant depertmant) {
		this.depertmant = depertmant;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public long getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(long mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	

}
