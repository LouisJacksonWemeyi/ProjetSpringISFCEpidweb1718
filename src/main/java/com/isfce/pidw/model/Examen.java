/**
 * 
 */
package com.isfce.pidw.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.isfce.pidw.model.Module.MAS;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wemeyi
 *
 */
@Data
@NoArgsConstructor
@Entity(name = "TEXAMEN")
public class Examen {
	
	@Id
	@Pattern(regexp = "[A-Z]{1,6}\\-[0-9]{0,2}[A-Z]{1,10}\\-[0-9]{1,2}\\-[A-Z]{1,2}", message = "{examen.code}")
	@Column(length = 26)
	private String codeExam;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "FKProf", nullable = false)
	private Professeur prof;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "FKModule", nullable = false)
	private Module module;
	
	@OneToMany(mappedBy="examen",cascade=CascadeType.PERSIST)
	protected Collection<Evaluation> evaluationlist= new ArrayList<>();

	public Examen(String codeExam, Professeur prof, Module module) {
		super();
		this.codeExam = codeExam;
		this.prof = prof;
		this.module = module;
	}

}
