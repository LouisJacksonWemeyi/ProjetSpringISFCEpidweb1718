/**
 * 
 */
package com.isfce.pidw.data;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isfce.pidw.model.Evaluation;

/**
 * @author Wemeyi
 *
 */
public interface IEvaluationJpaDAO extends JpaRepository<Evaluation, String>{


	@Query(value="select ev.* from TEVALUATION ev inner join TEXAMEN ex on ex.CODEEXAM=ev.FKEXAMEN where ex.FKMODULE=?", nativeQuery=true)
	List<Evaluation> getEvaluationOfExamen(String codeModule);
	
}
