/**
 * 
 */
package com.isfce.pidw.web.testsunitaires;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.isfce.pidw.config.DataConfig;
import com.isfce.pidw.data.ICompetenceJpaDAO;
import com.isfce.pidw.data.IEtudiantJpaDAO;
import com.isfce.pidw.data.IEvaluationJpaDAO;
import com.isfce.pidw.data.IModuleJpaDAO;

/**
 * @author Wemeyi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "testU")
@ContextConfiguration(classes = DataConfig.class)
public class MethodesDAOTest {
	@Autowired
	private IEtudiantJpaDAO etudiantDAO;
	@Autowired
	private IModuleJpaDAO moduleDAO;
	@Autowired
	private IEvaluationJpaDAO evaluationDAO;
	@Autowired
	private ICompetenceJpaDAO competenceDAO;
	
	
	@Test
	@Transactional
	public void testGet() throws ParseException {
		
	assertEquals(3,competenceDAO.getCompetencesOfCours("IPID").size()); // le cours PID a 3 competences
	assertEquals("NomEt1",etudiantDAO.findOne("Et1").getNom()); // verifie que le nom de l'etudiant Et1 est NomEt1
	assertEquals(3,moduleDAO.getModulesOfEtudiant("Et2").size()); //l'etudiant Et2 est inscrit à 3 modules
	assertEquals(1,evaluationDAO.getEvaluationOfExamen("IPID").size()); // vérifie qu'il existe 1 examen IPID




		
	}
	
}
