package edu.ban7.avengers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ban7.avengers.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;

@SpringBootTest
class TestIntegration {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()) // a ajouter si spring security
                .build();
    }

    @Test
    void getUser_shouldReturnUserWith200() throws Exception {
        mvc.perform(get("/utilisateur/{id}",1))
           .andExpect(status().isOk());
    }

    @Test
    void getNotExistingUser_shouldReturn404() throws Exception {
        mvc.perform(get("/utilisateur/{id}",99))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUser_shouldReturnUserWithoutPassword() throws Exception {
        mvc.perform(get("/utilisateur/{id}",1))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    void createUserAsAnonymous_shouldReturn403() throws Exception {

        //On créait un tilisateur en JAVA (note : on pourrait juste envoyé du JSON)
        Utilisateur nouvelUtilisateur = new Utilisateur();
        nouvelUtilisateur.setPassword("password");
        nouvelUtilisateur.setEmail("d@d");
        nouvelUtilisateur.setNom("dd");
        nouvelUtilisateur.setPrenom("ddd");

        //on convertir l'utilisateur en JSON (nécessite un Bean ObjectMapper, placé dans le ficgier de config)
        String json = mapper.writeValueAsString(nouvelUtilisateur);

        mvc.perform(post("/utilisateur")
                        .contentType(MediaType.APPLICATION_JSON.toString())
                        .accept(MediaType.APPLICATION_JSON.toString())
                        .content(json))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createUserAsAdmin_shouldReturn201() throws Exception {

        //On créait un utilisateur en JAVA (note : on pourrait juste envoyé du JSON)
        Utilisateur nouvelUtilisateur = new Utilisateur();
        nouvelUtilisateur.setPassword("password");
        nouvelUtilisateur.setEmail("d@d");
        nouvelUtilisateur.setNom("dd");
        nouvelUtilisateur.setPrenom("ddd");

        //on convertir l'utilisateur en JSON (nécessite un Bean ObjectMapper, placé dans le fichier de config)
        String json = mapper.writeValueAsString(nouvelUtilisateur);

        mvc.perform(post("/utilisateur")
                        .contentType(MediaType.APPLICATION_JSON.toString())
                        .accept(MediaType.APPLICATION_JSON.toString())
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("c@c")
    void deleteOrganisationAsNotCreatorNotAdmin_shouldReturn403() throws Exception {

        mvc.perform(delete("/organisation/{id}", 2))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("b@b")
    void deleteOrganisationAsCreatorNotAdmin_shouldReturn204() throws Exception {

        mvc.perform(delete("/organisation/{id}", 2))
                .andExpect(status().isNoContent());
    }
}
