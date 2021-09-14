/**
 * 
 */
package com.isfce.pidw.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isfce.pidw.model.Cours;
import com.isfce.pidw.model.Examen;

/**
 * @author Wemeyi
 *
 */
@Repository
public interface IExamenJpaDAO extends JpaRepository<Examen, String>{

}
