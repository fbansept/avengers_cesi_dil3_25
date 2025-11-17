package edu.ban7.avengers.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.avengers.view.OrganisationView;
import edu.ban7.avengers.view.UtilisateurView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(OrganisationView.class)
    protected Integer id;

    @Column(unique = true, nullable = false)
    @JsonView({UtilisateurView.class , OrganisationView.class})
    protected String nom;

    @ManyToOne(optional = false)
    @JsonView(OrganisationView.class)
    protected TypeOrganisation type;

    @ManyToMany(mappedBy = "organisations")
    @JsonView(OrganisationView.class)
    protected List<Utilisateur> utilisateurs = new ArrayList<>();

}
