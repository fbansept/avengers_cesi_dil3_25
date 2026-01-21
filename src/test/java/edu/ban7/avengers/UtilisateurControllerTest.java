package edu.ban7.avengers;

import edu.ban7.avengers.controller.UtilisateurController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class UtilisateurControllerTest {

    UtilisateurController utilisateurController;

    @BeforeEach
    public void setup() {
        utilisateurController = new UtilisateurController();
        MockUtilisateurDao mockUtilisateurDao = new MockUtilisateurDao();
        utilisateurController.setUtilisateurDao(mockUtilisateurDao);
    }

    @Test
    public void getExistingUser_shouldReturnUserWith200 (){
        Assertions.assertEquals(HttpStatus.OK, utilisateurController.get(1).getStatusCode());
    }

    @Test
    public void getNotExistingUser_shouldReturn404 (){
        Assertions.assertEquals(HttpStatus.NOT_FOUND, utilisateurController.get(2).getStatusCode());
    }

}
