package com.example.AppPokemon.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_pokemons")
@Schema(name = "Pokemon", description = "Entidad Pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado", example = "1")
    private Long pokemonId;
    @Schema(description = "Nombre del pokemon", example = "Charizard")
    private String nombre;
    @Schema(description = "Tipo del pokemon", example = "Fuego")
    private String tipo;
    @Schema(description = "Puntos de ataque", example = "444")
    private int ataque;
    @Schema(description = "Puntos de defensa", example = "444")
    private int defensa;
}
