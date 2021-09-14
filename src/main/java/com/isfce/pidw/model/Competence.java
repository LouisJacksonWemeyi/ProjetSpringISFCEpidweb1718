/**
 * 
 */
package com.isfce.pidw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wemeyi
 *
 */
@Data
@NoArgsConstructor
@Entity(name = "TCOMPETENCE")
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"code", "competence"})
	}) 

public class Competence {
	
	@Id
	@Column 
	private int competenceId;
	
	@Pattern(regexp = "[C]{1}[1-6]{1}", message = "{competence.code}")
	@Column(length = 2)
	private String code;
	
	@Column 
	private String competence;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "FKCours", nullable = false)
	private Cours cours;

	public Competence(int competenceId, String code, String competence, Cours cours) {
		super();
		this.competenceId = competenceId;
		this.code = code;
		this.competence = competence;
		this.cours = cours;
	}
	
}
