package edu.ban7.avengers.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.avengers.dao.OrganisationDao;
import edu.ban7.avengers.model.Organisation;
import edu.ban7.avengers.model.Utilisateur;
import edu.ban7.avengers.security.AppUserDetails;
import edu.ban7.avengers.security.IsUser;
import edu.ban7.avengers.view.OrganisationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @IsUser
    public ResponseEntity<Void> supprimer(
            @PathVariable int id,
            @AuthenticationPrincipal AppUserDetails userDetails) {

        Optional<Organisation> optionalOrganisation = organisationDao.findById(id);

        //si l'organisation n'existe pas, on renvoie un code 404
        if(optionalOrganisation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Utilisateur createur = optionalOrganisation.get().getCreateur();

        //si l'utilisateur n'est ni admin, ni créateur de l'organisation on renvoie un code 403
        if(!userDetails.getUtilisateur().isAdmin() &&
                !userDetails.getUtilisateur().getId().equals(createur.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        organisationDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/organisation")
    @JsonView(OrganisationView.class)
    public Organisation modifier(@RequestBody Organisation organisation) {

        organisationDao.save(organisation);

        return organisation;
    }

}
