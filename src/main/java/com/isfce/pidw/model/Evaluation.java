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

import com.isfce.pidw.model.Module.MAS;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Wemeyi
 *
 */
@Data
@NoArgsConstructor
@Entity(name = "TEVALUATION")
public class Evaluation {
	
	@Id
	private int evaluationId;
	
	@Setter
	@NotNull
	@Column
	private int session;

	@Column
	private String note;
	
	@Column
	private String remarque;

	@OneToMany(mappedBy="evaluation",cascade=CascadeType.PERSIST)
	protected Collection<Evaluationcompetence> competencelist= new ArrayList<>();
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "FKEtudiant", nullable = false)
	private Etudiant etudiant;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "FKExamen", nullable = false)
	private Examen examen;

	public Evaluation(int evaluationId, int session, String note, String remarque, Etudiant etudiant,Examen examen) {
		super();
		this.evaluationId = evaluationId;
		this.session = session;
		this.note = note;
		this.remarque = remarque;
		this.etudiant = etudiant;
		this.examen = examen;
	}
	
	
}
