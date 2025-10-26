package com.example.AppPokemon.service;

import com.example.AppPokemon.entity.Pokemon;

import java.util.List;

public interface PokemonService {

    List<Pokemon> findAllPokemons();

    Pokemon findPokemonById(Long pokemonId);

    Pokemon savePokemon(Pokemon pokemon);

    Pokemon updatePokemon(Long pokemonId, Pokemon pokemon);

    void deletePokemon(Long pokemonId);
}
