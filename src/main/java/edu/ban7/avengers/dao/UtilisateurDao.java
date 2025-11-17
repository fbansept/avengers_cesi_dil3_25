package edu.ban7.avengers.dao;

import edu.ban7.avengers.model.TypeOrganisation;
import edu.ban7.avengers.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

}
