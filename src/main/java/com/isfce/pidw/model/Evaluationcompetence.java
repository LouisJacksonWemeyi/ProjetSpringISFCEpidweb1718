/**
 * 
 */
package com.isfce.pidw.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.isfce.pidw.model.Module.MAS;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wemeyi
 *
 */
@Data
@NoArgsConstructor
@Entity(name = "TEVALUATIONCOMPETENCE")
public class Evaluationcompetence {

/*
public static enum EVALUATION {
		REUSSI, ECHEC
}
*/
	@Id
	private int evalcompetId;
	
	@NotNull
	@Column(nullable = false)
	private String resultat;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "FKCompetence", nullable = false)
	private Competence copetence;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "FKEvaluation", nullable = false)
	private Evaluation evaluation;

	public Evaluationcompetence(String resultat, Competence copetence, Evaluation evaluation) {
		super();
		this.resultat = resultat;
		this.copetence = copetence;
		this.evaluation = evaluation;
	}
	
}







