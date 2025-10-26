package com.example.AppPokemon.controller;

import com.example.AppPokemon.entity.Pokemon;
import com.example.AppPokemon.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pokemons", description = "Operaciones CRUD de Pokemons")
@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {


    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Listar Pokemons", description = "Retorna el listado completo de Pokemons")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pokemon.class)))
    @GetMapping("/getAllPokemons")
    public List<Pokemon> getAllPokemon(){
        return pokemonService.findAllPokemons();
    }

    @Operation(summary = "Obtener Pokemon por id")
    @ApiResponse(responseCode = "200", description = "Encontrado", content = @Content(schema = @Schema(implementation = Pokemon.class)))
    @ApiResponse(responseCode = "404", description = "No encontrado")
    @GetMapping("/getPokemon/{pokemonId}")
    public Pokemon getPokemon(@Parameter(description = "ID del pokemon", example = "1") @PathVariable Long pokemonId){
        return pokemonService.findPokemonById(pokemonId);
    }

    @Operation(summary = "Crear Pokemon")
    @ApiResponse(responseCode = "201", description = "Creado", content = @Content(schema = @Schema(implementation = Pokemon.class)))
    @PostMapping("/savePokemon")
    public Pokemon savePokemon(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del pokemon a crear", required = true,
            content = @Content(schema = @Schema(implementation = Pokemon.class))) @RequestBody Pokemon pokemon){
        return pokemonService.savePokemon(pokemon);
    }

    @Operation(summary = "Actualizar Pokemon")
    @ApiResponse(responseCode = "200", description = "Actualizado", content = @Content(schema = @Schema(implementation = Pokemon.class)))
    @PutMapping("/updatePokemon/{pokemonId}")
    public Pokemon updatePokemon(@Parameter(description = "Id del pokemon", example = "1") @PathVariable Long pokemonId, @RequestBody Pokemon pokemon){
        return pokemonService.updatePokemon(pokemonId, pokemon);
    }

    @Operation(summary = "Eliminar Pokemon")
    @ApiResponse(responseCode = "204", description = "Eliminado")
    @DeleteMapping("/deletePokemon/{pokemonId}")
    public String deletePokemon(@Parameter(description = "ID del pokemon", example = "1") @PathVariable Long pokemonId){
        pokemonService.deletePokemon(pokemonId);
        return "Pokemon eliminado correctamente";
    }

}
