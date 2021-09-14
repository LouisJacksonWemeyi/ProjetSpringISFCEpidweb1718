/**
 * 
 */
package com.isfce.pidw.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wemeyi
 *
 */

@Data
@NoArgsConstructor
public class EvalCompetence {
	private String resultat;
	private String code;
	private String competence;
	public EvalCompetence(String resultat, String code, String competence) {
		super();
		this.resultat = resultat;
		this.code = code;
		this.competence = competence;
	}
	
}
