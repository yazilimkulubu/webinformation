package org.yazilimkulubu.webinformation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with JBoss Developer Studio 6.0
 * User: Batuhan ÇIKRIKCI
 * Date: 13/10/13
 * Time: 13:55
 */

@Entity
@XmlRootElement
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="sender_member_id")
	private Member senderMember;
	
	@ManyToOne
	@JoinColumn(name="submited_member_id")
	private Member submitedMember;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Member getSenderMember() {
		return senderMember;
	}

	public void setSenderMember(Member senderMember) {
		this.senderMember = senderMember;
	}

	public Member getSubmitedMember() {
		return submitedMember;
	}

	public void setSubmitedMember(Member submitedMember) {
		this.submitedMember = submitedMember;
	}
	
}
