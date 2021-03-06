package com.isfce.pidw.web.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.isfce.pidw.config.DataConfig;
import com.isfce.pidw.data.ICoursJpaDAO;
import com.isfce.pidw.data.IModuleJpaDAO;
import com.isfce.pidw.data.IProfesseurJpaDAO;
import com.isfce.pidw.model.Cours;
import com.isfce.pidw.model.Module;
import com.isfce.pidw.model.Professeur;
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "testU")
@ContextConfiguration(classes = DataConfig.class)
public class CoursDAOTest {
	@Autowired
	ICoursJpaDAO coursDAO;
	@Autowired
	IModuleJpaDAO moduleDAO;
	@Autowired
	IProfesseurJpaDAO professeurDAO;
	
	@Test
	@Transactional
	public void testSaveGet() {
		long cptBf = coursDAO.count();
		Cours cours=new Cours("ITEST","Test JPA",(short)10);
		Cours coursSaved= coursDAO.save(cours);
		assertEquals(cours,coursSaved);
		Cours coursGet= coursDAO.getOne(coursSaved.getCode());
		assertEquals(coursSaved, coursGet);
		assertEquals(cptBf+1, coursDAO.count());
		//coursDAO.delete(coursSaved.getCode());
		coursDAO.delete(cours.getCode());
		assertEquals(cptBf, coursDAO.count());
		
	}
	
	@Test
	@Transactional
	public void testSection() throws ParseException {
		//sauve un cours sans section puis ajout de 2 sections
		Cours cours=new Cours("ITEST","Test JPA",(short)10);
		Cours coursSaved= coursDAO.save(cours);
		assertEquals(cours,coursSaved);
		//Ajout de 2 sections au cours
		coursSaved.addSection("Informatique");
		coursSaved.addSection("Comptabilit√©");
		//Recharge le cours
		Cours coursGet=coursDAO.getOne("ITEST");
		assertEquals(coursSaved,coursGet);
		
		
		//Sauve un cours avec sections
		Cours cours2=new Cours("ITEST2","Test JPA",(short)40);
		cours2.addSection("Informatique");
		cours2.addSection("Marketing");
		coursDAO.save(cours2);
		//Recharge du cours et ctrl sections
		coursGet= coursDAO.getOne("ITEST2");
		assertEquals(cours2, coursGet);
	    //Nbre de cours
		coursDAO.save(new Cours("SANSSEC","Test JPA",(short)20));
		assertEquals(3,coursDAO.count());
		List<Cours> liste=coursDAO.findAll();
		//V√©rifie les cours de la section info
		liste=coursDAO.readBySectionsIgnoringCase("Informatique");
		//Nbre de cours
		assertEquals(2,liste.size());
		assertEquals("ITEST",liste.get(0).getCode());
		assertEquals("ITEST2",liste.get(1).getCode());
		//V√©rifie les cours de la section Market
		liste=coursDAO.readBySectionsIgnoringCase("marketing");
		//Nbre de cours
		assertEquals(1,liste.size());
		assertEquals("ITEST2",liste.get(0).getCode());
		
		//V√©rifie que l'on a bien 3 sections
		List<String> listeSections=coursDAO.listeSections();
		assertEquals(3,listeSections.size());
		//V√©rifie si le nbr de section est bien de 2 pour ITEST
		List<String> sections=coursDAO.coursSection("ITEST");
		assertEquals(2,sections.size() );
		
		//Ajout de module √† un cours
		coursGet= coursDAO.getOne("ITEST");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Professeur vo= new Professeur("VO","VO","VO","Didier","voisfce@gmail.com");
		vo=professeurDAO.save(vo);
		Module m1 = new Module("4ITEST-1-A", sdf.parse("4/9/2017"), sdf.parse("22/1/2018"), Module.MAS.SOIR, coursGet, vo);
		Module m2 = new Module("4ITEST-2-A", sdf.parse("4/9/2017"), sdf.parse("22/1/2018"), Module.MAS.APM, coursGet,vo);
		//sauvegarde des cours
		m1=moduleDAO.save(m1);
		m2=moduleDAO.save(m2);
		List<Module> modules=moduleDAO.getModulesCours("ITEST");
		assertTrue(modules.contains(m1));
		assertTrue(modules.contains(m2));
		
		//Teste la relation bidirectionnelle
		Module m3 = new Module("4ITEST-3-A", sdf.parse("4/9/2017"), sdf.parse("22/1/2018"), Module.MAS.APM, coursGet,vo);
		//ajoute les liens bidirectionnels
		coursGet.getModules().add(m1);
		coursGet.getModules().add(m2);
		coursGet.getModules().add(m3);
		coursGet= coursDAO.getOne("ITEST");
		assertEquals(3, coursGet.getModules().size());
		assertEquals(3,moduleDAO.getModulesCours("ITEST").size());
		
	}
}
