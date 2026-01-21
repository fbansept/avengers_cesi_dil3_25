package edu.ban7.avengers.controller;

import edu.ban7.avengers.dao.UtilisateurDao;
import edu.ban7.avengers.model.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    protected final UtilisateurDao utilisateurDao;
    protected final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Ajoute un utilisateur en base de données
     *
     * @param utilisateur
     * @return
     */
    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> signIn(@RequestBody Utilisateur utilisateur) {

        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateurDao.save(utilisateur);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    /**
     * retourne un JWT
     *
     * @param utilisateur
     * @return
     */
    @PostMapping("/connexion")
    public ResponseEntity<String> logIn(@RequestBody Utilisateur utilisateur) {

        try {
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(utilisateur.getEmail(), utilisateur.getPassword())
            );
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        String jwt = Jwts
                .builder()
                .setSubject(utilisateur.getEmail())
                .signWith(SignatureAlgorithm.HS512, "azerty")
                .compact();

        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

}
