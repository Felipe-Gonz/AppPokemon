package com.example.AppPokemon.controller;

import com.example.AppPokemon.entity.Pokemon;
import com.example.AppPokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {


    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/getAllPokemons")
    public List<Pokemon> getAllPokemon(){
        return pokemonService.findAllPokemons();
    }

    @GetMapping("/getPokemon/{pokemonId}")
    public Pokemon getPokemon(@PathVariable Long pokemonId){
        return pokemonService.findPokemonById(pokemonId);
    }

    @PostMapping("/savePokemon")
    public Pokemon savePokemon(@RequestBody Pokemon pokemon){
        return pokemonService.savePokemon(pokemon);
    }

    @PutMapping("/updatePokemon/{pokemonId}")
    public Pokemon updatePokemon(@PathVariable Long pokemonId, @RequestBody Pokemon pokemon){
        return pokemonService.updatePokemon(pokemonId, pokemon);
    }

    @DeleteMapping("/deletePokemon/{pokemonId}")
    public String deletePokemon(@PathVariable Long pokemonId){
        pokemonService.deletePokemon(pokemonId);
        return "Pokemon eliminado correctamente";
    }

}
