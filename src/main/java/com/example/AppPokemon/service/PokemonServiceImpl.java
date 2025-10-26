package com.example.AppPokemon.service;

import com.example.AppPokemon.entity.Pokemon;
import com.example.AppPokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService{

    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> findAllPokemons() {
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon findPokemonById(Long pokemonId) {
        Optional<Pokemon> pokemonOptional = pokemonRepository.findById(pokemonId);
        return pokemonOptional.orElseThrow();
    }

    @Override
    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Override
    public Pokemon updatePokemon(Long pokemonId, Pokemon pokemon) {
        Optional<Pokemon> pokemonOptional = pokemonRepository.findById(pokemonId);
        Pokemon pokemonBD = pokemonOptional.orElseThrow();
        pokemonBD.setNombre(pokemon.getNombre());
        pokemonBD.setTipo(pokemon.getTipo());
        pokemonBD.setAtaque(pokemon.getAtaque());
        pokemonBD.setDefensa(pokemon.getDefensa());
        return pokemonRepository.save(pokemonBD);
    }

    @Override
    public void deletePokemon(Long pokemonId) {
        pokemonRepository.deleteById(pokemonId);
    }
}
