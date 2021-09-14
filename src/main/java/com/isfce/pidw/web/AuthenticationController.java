/**
 * 
 */
package com.isfce.pidw.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isfce.pidw.config.security.Roles;
import com.isfce.pidw.data.IUsersJpaDAO;
import com.isfce.pidw.model.Module;
import com.isfce.pidw.model.Users;

/**
 * @author Wemeyi
 *
 */
@Controller
@RequestMapping("/authenticaion")
public class AuthenticationController {

private IUsersJpaDAO<Users> usersDAO;
	
@Autowired
public AuthenticationController(IUsersJpaDAO<Users> usersDAO) {
	super();
	this.usersDAO = usersDAO;
}
    @GetMapping("/authentication")
	public String urlAuthentication(Principal principal) {

	Roles roleUser = usersDAO.getUserNameRole(principal.getName());
	
	if (Roles.ROLE_PROF.equals(roleUser)) { // renvoie vers la page index pour la liste des modules du prof
		
		return "redirect:/index";
	} 
	else // renvoie vers le controleur etudiant pour retourner la liste des modules de l'étudiant
		if (Roles.ROLE_ETUDIANT.equals(roleUser)) {
	
			return "redirect:/etudiant/listeModules/"+principal.getName();
	}
	return null;
	
}
}
