
package com.isfce.pidw.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isfce.pidw.model.Cours;




public interface ICoursJpaDAO extends JpaRepository<Cours, String> {
	
	List<Cours> readBySectionsIgnoringCase(String section);
	
	
	@Query(value="select section from TSECTION where FKCOURS=?", nativeQuery=true)
	List<String> coursSection(String codeCours);
	
	@Query(value="select distinct upper(section) from TSECTION ", nativeQuery=true)
	List<String> listeSections();

}
