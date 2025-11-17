package edu.ban7.avengers.controller;

import edu.ban7.avengers.dao.TypeOrganisationDao;
import edu.ban7.avengers.model.TypeOrganisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("type-organisation")
@CrossOrigin
public class TypeOrganisationController {

    @Autowired
    protected TypeOrganisationDao typeOrganisationDao;

    @GetMapping("/liste")
    public List<TypeOrganisation> liste() {

        //requete a la base de donnée
        //on transforme le SQL en objet JAVA
        return typeOrganisationDao.findAll();

        //le JAVA est transformé en JSON
    }

    @GetMapping("/{id}")
    public TypeOrganisation get(@PathVariable int id) {

        Optional<TypeOrganisation> optionalTypeOrganisation = typeOrganisationDao.findById(id);

        return optionalTypeOrganisation.orElse(null);
    }

    @PostMapping
    public TypeOrganisation ajout(@RequestBody TypeOrganisation typeOrganisation) {

        typeOrganisationDao.save(typeOrganisation);

        return typeOrganisation;
    }

    @DeleteMapping("/{id}")
    public boolean supprimer(@PathVariable int id) {

        typeOrganisationDao.deleteById(id);

        return true;
    }

    @PutMapping
    public TypeOrganisation modifier(@RequestBody TypeOrganisation typeOrganisation) {

        typeOrganisationDao.save(typeOrganisation);

        return typeOrganisation;
    }

}
