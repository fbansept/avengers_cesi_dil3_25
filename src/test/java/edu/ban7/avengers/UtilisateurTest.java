package edu.ban7.avengers;

import edu.ban7.avengers.model.Utilisateur;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class UtilisateurTest {

    Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validateUser_shouldBeValid() {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPrenom("John");
        utilisateur.setNom("Doe");
        utilisateur.setPassword("password");

        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        Assertions.assertTrue(violations.isEmpty());

    }

    @Test
    public void validateUserWithoutLastname_shouldBeInvalid() {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPrenom("John");
        utilisateur.setPassword("password");

        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("nom", violations.iterator().next().getPropertyPath().toString());
        Assertions.assertEquals("ne doit pas être vide", violations.iterator().next().getMessage());
    }

}
