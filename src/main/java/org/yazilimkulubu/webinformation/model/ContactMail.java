package org.yazilimkulubu.webinformation.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created with JBoss Developer Studio 6.0
 * User: Batuhan ÇIKRIKCI
 * Date: 17/09/13
 * Time: 14:00
 */

public class ContactMail {
	
	@NotEmpty(message="e-mail adresi boş olamaz!")
	@Email(message="Lütfen geçerli bir e-mail adresi girin.")
	private String fromAdress;
	
	@Size(min = 15,message="Mesajınız yeterli uzunlukta değil!")
	private String text;
	
	@Size(min = 1, max = 25,message="İsim 1 - 25 karakter arasında olmalıdır!")
	private String nameSurname;

	public String getFromAdress() {
		return fromAdress;
	}

	public void setFromAdress(String fromAdress) {
		this.fromAdress = fromAdress;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}
	

}
