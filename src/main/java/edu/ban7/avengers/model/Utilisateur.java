package edu.ban7.avengers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({UtilisateurView.class , OrganisationView.class})
    protected Integer id;

    @Column(nullable = false)
    @JsonView(UtilisateurView.class)
    protected String nom;

    @Column(nullable = false)
    @JsonView(UtilisateurView.class)
    protected String prenom;

    @Column(nullable = false)
    @JsonView(UtilisateurView.class)
    protected String email;

    @Column(nullable = false)
    protected String password;

    @ManyToMany
    @JoinTable(
            name = "organisation_utilisateur",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "organisation_id"))
    @JsonView(UtilisateurView.class)
    List<Organisation> organisations = new ArrayList<>();

}
