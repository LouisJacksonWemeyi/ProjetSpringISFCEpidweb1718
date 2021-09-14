package com.isfce.pidw.web;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isfce.pidw.config.security.Roles;
import com.isfce.pidw.data.IModuleJpaDAO;
import com.isfce.pidw.data.IUsersJpaDAO;
import com.isfce.pidw.model.Module;
import com.isfce.pidw.model.Users;

@Controller
@RequestMapping("/module")
public class ModuleController {
	// Logger
	final static Logger logger = Logger.getLogger(ModuleController.class);

	private IModuleJpaDAO moduleDAO;
	private IUsersJpaDAO<Users> usersDAO;

	@Autowired
	public ModuleController(IModuleJpaDAO moduleDAO, IUsersJpaDAO<Users> usersDAO) {
		super();
		this.moduleDAO = moduleDAO;
		this.usersDAO = usersDAO;
	}

	/**
	 * Liste des modules ou liste de l'utilisateur précisé (Prof ou étudiant) un
	 * étudiant ne peut pas voir les modules d'un autre élève que lui-même
	 * 
	 * @param codeUser
	 *            username 1) si vide ==> retourne la liste de tous les modules 2)
	 *            si prof ==> retourne la liste des modules donnés par le prof 3) si
	 *            étudiant ==> retourne la liste des modules dans lesquels il est
	 *            inscrit (ne peut pas que ses inscriptions)
	 * 
	 * @param model
	 * @param authentication
	 *            droits de l'utilisateur connecté
	 * @return nom logique de la vue
	 */

	@RequestMapping(value = { "/liste/{codeUser}", "/liste" })
	public String listeModules(@PathVariable Optional<String> codeUser, Model model, Authentication authentication) {
		List<Module> lm = null;
		String texte = null;

		logger.debug(" user connecté: " + (authentication == null ? " NULL " : authentication.getName()));
		// si on ne précise pas de "codeUser"
		if (!codeUser.isPresent()) {
			lm = moduleDAO.findAll();
			texte = "de l'école";

		} else {
			// role de codeUser
			Roles roleUser = usersDAO.getUserNameRole(codeUser.get());

			// si le code user est un prof retourne les modules du prof
			if (Roles.ROLE_PROF.equals(roleUser)) {
				lm = moduleDAO.readByProfesseurIsNotNull(codeUser.get());
				texte = "du professeur: " + codeUser.get();
				logger.debug("Modules du prof " + codeUser.orElse("vide: ") + "NB Modules :" + lm.size());
			} else // renvoie la liste des modules de l'étudiant (et contrôle les droits)
			if (Roles.ROLE_ETUDIANT.equals(roleUser)) {
				// il faut être authentifié et si on est un étudiant on ne peut voir que ses
				// propres modules. Déclenche une exception autrement!
				if (authentication == null || 
						(!authentication.getName().equals(codeUser.get()) && authentication
						.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(Roles.ROLE_ETUDIANT.name()))))
					throw new NoAccessException("Doit être connecté comme prof, admin ou l'étudiant " + codeUser.get());
				
				lm = moduleDAO.getModulesOfEtudiant(codeUser.get());
				texte = "de l'étudiant: " + codeUser.get();
				logger.debug("Modules de l'étudiant " + codeUser.orElse("vide: ") + "NB Inscriptions :" + lm.size());
			} else texte= " (user inconnu: "+codeUser.get()+")";
		}
		model.addAttribute("userModules", texte);// le texte pour indiquer de qui sont les modules
		model.addAttribute("moduleList", lm);// liste des modules
		return "module/listeModules";
	}
	/*
	@GetMapping("/liste/{username}")
	   public String userModules(@PathVariable("username") String username,Model model) {
		
		List<Module> lm = null;
		Roles roleUser = usersDAO.getUserNameRole(username);
		
		if (Roles.ROLE_PROF.equals(roleUser)) { // renvoie la liste des modules du prof
			
			lm = moduleDAO.readByProfesseurIsNotNull(username);
		    model.addAttribute("moduleListProf", lm);
			logger.debug("Modules du prof " + username + "NB Modules :" + lm.size());
			
		} else // renvoie la liste des modules de l'étudiant
			if (Roles.ROLE_ETUDIANT.equals(roleUser)) {
		
			lm = moduleDAO.getModulesOfEtudiant(username);
		    model.addAttribute("moduleListEtudiant", lm);
			logger.debug("Modules de l'étudiant " + username + "NB Inscriptions :" + lm.size());
		} 
	      return "module/listedesmodules";
	}
	*/

}
