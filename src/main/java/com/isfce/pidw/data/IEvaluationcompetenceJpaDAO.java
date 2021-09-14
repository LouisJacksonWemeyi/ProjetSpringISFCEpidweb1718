/**
 * 
 */
package com.isfce.pidw.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isfce.pidw.model.EvalCompetence;
import com.isfce.pidw.model.Evaluation;
import com.isfce.pidw.model.Evaluationcompetence;

import java.util.List;

/**
 * @author Wemeyi
 *
 */
@Repository
public interface IEvaluationcompetenceJpaDAO extends JpaRepository<Evaluationcompetence, Integer>{

	@Query(value="select e.RESULTAT,c.CODE,c.COMPETENCE from TEVALUATIONCOMPETENCE e inner join TCOMPETENCE c on e.FKCOMPETENCE=c.COMPETENCEID where e.FKEVALUATION=?", nativeQuery=true)
	List<EvalCompetence> getEvaluationOfCompetence(String competenceId);
}
