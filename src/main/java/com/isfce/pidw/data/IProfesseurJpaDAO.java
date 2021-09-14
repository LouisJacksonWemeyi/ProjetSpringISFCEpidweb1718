package com.isfce.pidw.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isfce.pidw.model.Etudiant;
import com.isfce.pidw.model.Professeur;

@Repository
public interface IProfesseurJpaDAO extends IUsersJpaDAO<Professeur> {

}
