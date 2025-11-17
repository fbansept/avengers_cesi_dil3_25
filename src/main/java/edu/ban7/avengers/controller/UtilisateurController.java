package edu.ban7.avengers.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.avengers.dao.UtilisateurDao;
import edu.ban7.avengers.model.Utilisateur;
import edu.ban7.avengers.view.UtilisateurView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("utilisateur")
@CrossOrigin
public class UtilisateurController {

    @Autowired
    protected UtilisateurDao utilisateurDao;

    @GetMapping("/liste")
    @JsonView(UtilisateurView.class)
    public List<Utilisateur> liste() {

        //requete a la base de donnée
        //on transforme le SQL en objet JAVA
        return utilisateurDao.findAll();

        //le JAVA est transformé en JSON
    }

    @GetMapping("/{id}")
    @JsonView(UtilisateurView.class)
    public Utilisateur get(@PathVariable int id) {

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        return optionalUtilisateur.orElse(null);
    }

    @PostMapping
    @JsonView(UtilisateurView.class)
    public Utilisateur ajout(@RequestBody Utilisateur utilisateur) {

        utilisateurDao.save(utilisateur);

        return utilisateur;
    }

    @DeleteMapping("/{id}")
    public boolean supprimer(@PathVariable int id) {

        utilisateurDao.deleteById(id);

        return true;
    }

    @PutMapping
    @JsonView(UtilisateurView.class)
    public Utilisateur modifier(@RequestBody Utilisateur utilisateur) {

        utilisateurDao.save(utilisateur);

        return utilisateur;
    }

}
