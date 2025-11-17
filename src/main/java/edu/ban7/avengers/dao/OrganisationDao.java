package edu.ban7.avengers.dao;

import edu.ban7.avengers.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationDao extends JpaRepository<Organisation, Integer> {

}
