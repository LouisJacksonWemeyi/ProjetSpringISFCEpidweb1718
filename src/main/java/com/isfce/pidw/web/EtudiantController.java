/**
 * 
 */
package com.isfce.pidw.web;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isfce.pidw.config.security.GeneratePassword;
import com.isfce.pidw.config.security.Roles;
import com.isfce.pidw.data.ICompetenceJpaDAO;
import com.isfce.pidw.data.IEtudiantJpaDAO;
import com.isfce.pidw.data.IEvaluationJpaDAO;
import com.isfce.pidw.data.IEvaluationcompetenceJpaDAO;
import com.isfce.pidw.data.IModuleJpaDAO;
import com.isfce.pidw.model.Competence;
import com.isfce.pidw.model.Etudiant;
import com.isfce.pidw.model.EvalCompetence;
import com.isfce.pidw.model.Evaluation;
import com.isfce.pidw.model.Module;

/**
 * @author Wemeyi
 *
 */
@Controller
@RequestMapping("/etudiant")
public class EtudiantController {
	
// Logger
final static Logger logger = Logger.getLogger(EtudiantController.class);

private IEtudiantJpaDAO etudiantDAO;
private IModuleJpaDAO moduleDAO;
private IEvaluationJpaDAO evaluationDAO;
private ICompetenceJpaDAO evalcompetDAO;
Principal principal;

@Autowired
public EtudiantController (IEtudiantJpaDAO etudiantDAO,IModuleJpaDAO moduleDAO,IEvaluationJpaDAO evaluationDAO,ICompetenceJpaDAO evalcompetDAO) {
	super();
	this.etudiantDAO = etudiantDAO;
	this.moduleDAO = moduleDAO;
	this.evaluationDAO=evaluationDAO;
	this.evalcompetDAO=evalcompetDAO;

}


// Liste des etudiants
@RequestMapping("/listeEtudiants")
public String listeEtudiants(Model model) {
	model.addAttribute("etudiants", etudiantDAO.findAll());
	return "etudiant/listeEtudiants";
}

//montre le formulaire d'ajout d'un étudiant
	@RequestMapping(value = "/ajoutform", method = RequestMethod.GET)
	public String showAddetudiantForm(Model model) {

		logger.debug("showAddetudiantForm()");

		//Etudiant etudiant = new Etudiant("ET1","ET1","NomET1","PrénomET1","et1@isfce.org","0477060035");

		model.addAttribute("etudiantForm", new Etudiant("ET1","ET1","NomET1","PrénomET1","et1@isfce.org","0477060035"));
		
		//populateDefaultModel(model);

		return "etudiant/etudiantFormInt";

	}
	
// save or update etudiant
// 1. @ModelAttribute bin form value
// 2. @Validated form validator
// 3. RedirectAttributes for flash value
@RequestMapping(value = "/addEtudiant", method = RequestMethod.POST)
public String saveOrUpdateEtudiant(@ModelAttribute("etudiantForm") @Validated Etudiant etudiant,
		BindingResult result, Model model,
		final RedirectAttributes redirectAttributes) {

	logger.debug("saveOrUpdateEtudiant() : {} "+ etudiant + etudiant.getUsername() + etudiant.getPassword());

	if (result.hasErrors()) {
		//populateDefaultModel(model);
		return "etudiant/etudiantForm";
	} else {

		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");
		if(etudiantDAO.exists(etudiant.getUsername())){
		  redirectAttributes.addFlashAttribute("msg", "Etudiant updated successfully!");
		}else{
		  redirectAttributes.addFlashAttribute("msg", "Etudiant added  successfully!");
		}
		logger.debug("saveOrUpdateEtudiant() : {}"+ etudiant);
		
		saveOrUpdate(etudiant);
		

		// POST/REDIRECT/GET
		return "redirect:/etudiant/" + etudiant.getUsername();

		// POST/FORWARD/GET
		// return "user/list";
	}

}

// show etudiant
@RequestMapping(value = "/{usernane}", method = RequestMethod.GET)
public String showEtudiant(@PathVariable("usernane") String username, Model model) {

	logger.debug("showEtudiant() username: {}"+ username);

	Etudiant etudiant = etudiantDAO.findOne(username);
	if (etudiant == null) {
		model.addAttribute("css", "danger");
		model.addAttribute("msg", "Etudiant not found");
	}
	model.addAttribute("etudiant", etudiant);

	return "etudiant/showEtudiant";

}

//show update etudiant form
	@RequestMapping(value = "/{username}/update", method = RequestMethod.GET)
	public String showUpdateEtudiantForm(@PathVariable("username") String username, Model model) {

		logger.debug("showUpdateEtudiantForm() : {}"+ username);
	
		Etudiant etudiant = etudiantDAO.findOne(username);
		model.addAttribute("savedId", etudiant.getUsername());
		model.addAttribute("etudiantForm", etudiant);

		//populateDefaultModel(model);

		return "etudiant/etudiantForm";

	}

	// delete etudiant
	@RequestMapping(value = "/{username}/delete", method = RequestMethod.POST)
	public String deleteEtudiant(@PathVariable("username") String username,
		final RedirectAttributes redirectAttributes) {

		logger.debug("deleteEtudiant() : {}"+ username);

		etudiantDAO.delete(username);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Etudiant "+username+"  is deleted!");

		return "redirect:/etudiant/listeEtudiants";

	}
	
	@GetMapping("/listeModules/{username}")
	   public String userModules(@PathVariable("username") String username,Model model) {
	    SecurityContext context = SecurityContextHolder.getContext();
		List<Module> lm = null;

		// renvoie la liste des modules de l'étudiant connecté
		if (context.getAuthentication().getName().trim().equals(username)) {
			lm = moduleDAO.getModulesOfEtudiant(username);
			model.addAttribute("moduleListEtudiant", lm);
			logger.debug("Modules de l'étudiant " + username + " NB Inscriptions :" + lm.size());

			return "etudiant/listedesModules";
		} else {
			return "login";
		}
	}


	@GetMapping("/evaluation/{codeModule}")
	   public String userModulesevaluation(@PathVariable("codeModule") String codeModule, Model model) {
		List<Evaluation> evaluation = null;

		// renvoie l'evalution du module de l'étudiant
		
			evaluation = evaluationDAO.getEvaluationOfExamen(codeModule.trim());
		    model.addAttribute("evaluationModule",evaluation);
		    SecurityContext context = SecurityContextHolder.getContext();
			logger.debug("Evaluation du module " + codeModule + "de l'étudiant :"+ context.getAuthentication().getName());
	      return "etudiant/evaluationModule";
	}


	//@SuppressWarnings("null")
	@GetMapping("evalCompetences/{evaluationId}")
	   public String userCompetencesEvaluation(@PathVariable("evaluationId") String evaluationId,Model model) {
		List<Competence> competReussi = null;
		List<Competence> competEchec = null;

	    SecurityContext context = SecurityContextHolder.getContext();
	    
		// renvoie les compétences évaluées du module de l'étudiant
		
			competReussi = evalcompetDAO.getCompetenceReussiOfExamen(evaluationId);
			competEchec = evalcompetDAO.getCompetenceEchecOfExamen(evaluationId);
			int nbCompet=competReussi.size() + competEchec.size();
		    model.addAttribute("listcompetReussi",competReussi);
		    model.addAttribute("listcompetEchec",competEchec);
		    model.addAttribute("nbCompet",nbCompet);

			logger.debug("Liste des competences évaluées de l'evaluation " + evaluationId + " de l'étudiant :" + context.getAuthentication().getName());
		
	      return "etudiant/evaluationcompetence";
	}
	
public void saveOrUpdate(Etudiant etudiant) {
	etudiant.setPassword(new GeneratePassword().encoder().encode(etudiant.getPassword()));
	etudiant.setRole(Roles.ROLE_ETUDIANT);
	
	if (etudiantDAO.exists(etudiant.getUsername())) {
		etudiantDAO.delete(etudiant.getUsername());
		etudiantDAO.save(etudiant);
	} else {
		etudiantDAO.save(etudiant);
	}
}

}
