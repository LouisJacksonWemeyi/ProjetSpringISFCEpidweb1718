package com.isfce.pidw.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.isfce.pidw.model.Module;

@Repository
public interface IModuleJpaDAO extends JpaRepository<Module, String> {
	@Query(value="select * from TMODULE where FKCours=?", nativeQuery=true)
	List<Module> getModulesCours(String codeCours);
	
	@Query("SELECT m FROM TMODULE m WHERE m.moment= ?2 and (?1 MEMBER OF m.cours.sections)")
	List<Module> getModulesAPMFromSection(String section, Module.MAS mas);
	
	
	//@PreAuthorize("#username == principal.username")
	@Query(value="select m.* from TMODULE m inner join TINSCRIPTION i on i.FKMODULE=m.CODE where i.FKETUDIANT=?", nativeQuery=true)
	List<Module> getModulesOfEtudiant(String username);
	
	@Query("select m from TMODULE m  where m.prof.username=?1")
	List<Module> readByProfesseurIsNotNull(String username);
}
