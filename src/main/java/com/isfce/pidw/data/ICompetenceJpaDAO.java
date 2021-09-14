package com.isfce.pidw.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isfce.pidw.model.Competence;
import com.isfce.pidw.model.Cours;
import com.isfce.pidw.model.Evaluation;

@Repository
public interface ICompetenceJpaDAO extends JpaRepository<Competence, Integer>{
	
	
	@Query(value="select c.* from TCOMPETENCE c inner join TEVALUATIONCOMPETENCE e on e.FKCOMPETENCE=c.COMPETENCEID where e.RESULTAT='REUSSI' AND e.FKEVALUATION=?", nativeQuery=true)
	List<Competence> getCompetenceReussiOfExamen(String competenceId);
	@Query(value="select c.* from TCOMPETENCE c inner join TEVALUATIONCOMPETENCE e on e.FKCOMPETENCE=c.COMPETENCEID where e.RESULTAT='ECHEC' AND e.FKEVALUATION=?", nativeQuery=true)
	List<Competence> getCompetenceEchecOfExamen(String competenceId);
	
	@Query(value="select c.* from TCOMPETENCE c where c.FKCOURS=?", nativeQuery=true)
	List<Competence> getCompetencesOfCours(String codeCours);
}
