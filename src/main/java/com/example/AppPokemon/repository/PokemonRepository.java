package com.example.AppPokemon.repository;

import com.example.AppPokemon.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    @Query("SELECT P FROM Pokemon P")
    List<Pokemon> getAllPokemons();
}
