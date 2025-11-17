package edu.ban7.avengers.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.avengers.dao.OrganisationDao;
import edu.ban7.avengers.model.Organisation;
import edu.ban7.avengers.view.OrganisationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class OrganisationController {

    @Autowired
    protected OrganisationDao organisationDao;

    @GetMapping("/organisation/liste")
    @JsonView(OrganisationView.class)
    public List<Organisation> liste() {

        //requete a la base de donnée
        //on transforme le SQL en objet JAVA
        return organisationDao.findAll();

        //le JAVA est transformé en JSON
    }

    @GetMapping("/organisation/{id}")
    @JsonView(OrganisationView.class)
    public Organisation get(@PathVariable int id) {

        Optional<Organisation> optionalOrganisation = organisationDao.findById(id);

        return optionalOrganisation.orElse(null);
    }

    @PostMapping("/organisation")
    @JsonView(OrganisationView.class)
    public Organisation ajout(@RequestBody Organisation organisation) {

        organisationDao.save(organisation);

        return organisation;
    }

    @DeleteMapping("/organisation/{id}")
    public boolean supprimer(@PathVariable int id) {

        organisationDao.deleteById(id);

        return true;
    }

    @PutMapping("/organisation")
    @JsonView(OrganisationView.class)
    public Organisation modifier(@RequestBody Organisation organisation) {

        organisationDao.save(organisation);

        return organisation;
    }

}
