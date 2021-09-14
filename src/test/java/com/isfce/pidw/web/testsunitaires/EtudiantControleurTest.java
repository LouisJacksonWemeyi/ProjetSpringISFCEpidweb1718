/**
 * 
 */
package com.isfce.pidw.web.testsunitaires;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.isfce.pidw.data.ICompetenceJpaDAO;
import com.isfce.pidw.data.IEtudiantJpaDAO;
import com.isfce.pidw.data.IEvaluationJpaDAO;
import com.isfce.pidw.data.IModuleJpaDAO;
import com.isfce.pidw.model.Etudiant;
import com.isfce.pidw.web.EtudiantController;

/**
 * @author Wemeyi
 *
 */
public class EtudiantControleurTest {
	@Autowired
	IEtudiantJpaDAO mockEtudiantDAO;
	@Autowired
	IModuleJpaDAO mockModuleDAO;
	@Autowired
	IEvaluationJpaDAO mockEvaluationDAO;
	@Autowired
	ICompetenceJpaDAO mockEvalcompetDAO;
	
	@Test
	public void testUpdateEtudiantGetPost() throws Exception {
		IEtudiantJpaDAO mockEtudiantDAO=mock(IEtudiantJpaDAO.class);
		IModuleJpaDAO mockModuleDAO=mock(IModuleJpaDAO.class);
		IEvaluationJpaDAO mockEvaluationDAO=mock(IEvaluationJpaDAO.class);
		ICompetenceJpaDAO mockEvalcompetDAO=mock(ICompetenceJpaDAO.class);
		Etudiant oldSaved = new Etudiant("ET1","ET1","NomET1","PrenomET1","et1@isfce.org","0477060035");
		Etudiant newSaved = new Etudiant("ET11","ET11","NomET11","PrenomET11","et11@isfce.org","0477060035");
		when(mockEtudiantDAO.findOne("ET1")).thenReturn(oldSaved);
		when(mockEtudiantDAO.exists("ET1")).thenReturn(true);
		when(mockEtudiantDAO.exists("ET11")).thenReturn(false);
		when(mockEtudiantDAO.save(newSaved)).thenReturn(newSaved);

		EtudiantController controller = new EtudiantController(mockEtudiantDAO,mockModuleDAO,mockEvaluationDAO,mockEvalcompetDAO);

		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/etudiant/ET1/update")).andExpect(view().name("etudiant/etudiantForm"));

		mockMvc.perform(post("/etudiant/addEtudiant").param("username", "ET1").param("password", "ET1")
				.param("nom", "NomET1").param("prenom", "PrenomET1").param("email", "et1@isfce.org").param("tel", "0477060035").param("savedId", "ET1"))
				.andExpect(redirectedUrl("/cours/showEtudiant"));

		verify(mockEtudiantDAO, atLeastOnce()).save(newSaved);
		verify(mockEtudiantDAO, atLeastOnce()).delete(oldSaved.getUsername());

	}

}
