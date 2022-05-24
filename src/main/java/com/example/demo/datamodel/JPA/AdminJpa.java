package com.example.demo.datamodel.JPA;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.domain.valueobjects.PersonId;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="admins")
public class AdminJpa {

	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode
	@Embeddable
	static class GroupPersonIdJpa implements Serializable {
		private static final long serialVersionUID = 4517981563951180888L;
	
		@Getter
		@ManyToOne
		@JoinColumn(name = "group_id")
		private GroupJpa groupJpa;	
	
		@Getter
		@Column(nullable = false, updatable = false)
		private PersonId personId;
	}

	@Getter
	@EmbeddedId
	GroupPersonIdJpa id;

	public AdminJpa(GroupJpa groupJpa, PersonId personId ) {
		this.id = new GroupPersonIdJpa(groupJpa, personId);
	}

	public PersonId getPersonId() {
		return id.getPersonId();
	}
}
