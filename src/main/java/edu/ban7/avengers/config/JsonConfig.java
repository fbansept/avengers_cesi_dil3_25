package edu.ban7.avengers.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {

        //n'ajoute pas automatiquement les propriétés des models dans le JSON,
        // mais se base sur les JsonView
        return builder -> builder.defaultViewInclusion(false);
    }
}
