package com.example.AppPokemon.config;

import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;


public class OpenApiConfig {

    @Bean
    public OpenAPI appPokemonOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                    .title("AppPokemon API")
                    .description("API para administrar Pokemons (CRUD)")
                    .version("v1")
                    .contact(new Contact()
                            .name("Equipo AppPokkemon")
                            .email("fg180899@gmail.com"))
                    .license(new License().name("MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio/Documentacion")
                        .url("https://www.wikidex.net/wiki/Caracter%C3%ADsticas_(Pok%C3%A9mon_GO)"));
    }
}
