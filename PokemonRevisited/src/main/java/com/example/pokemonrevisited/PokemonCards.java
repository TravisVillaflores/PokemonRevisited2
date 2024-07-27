package com.example.pokemonrevisited;

import java.util.ArrayList;

public class PokemonCards {
    private ArrayList<Pokemon> pokemons;

    public PokemonCards() {
        pokemons = new ArrayList<>();
        loadStats();
    }

    private void loadStats() {
        String[] data = {
                "Squirtle,9.0kg,0.5m,0.55,0.35,0.56,Water",
                "Wartortle,22.5kg,1.0m,0.50,0.37,0.6,Water",
                "Blastoise,85.5kg,1.6m,0.90,0.98,0.32,Water",
                "Rattata,3.5kg,0.3m,0.28,0.99,0.54,Normal",
                "Raticate,18.5kg,0.7m,0.50,0.30,0.65,Normal",
                "Snorlax,460.0kg,2.1m,0.90,0.85,0.98,Normal",
                "Eevee,6.5kg,0.3m,0.30,0.28,0.23,Normal",
                "Flareon,25.0kg,0.9m,0.50,0.25,0.60,Fire",
                "Jolteon,24.5kg,0.8m,0.90,0.55,0.30,Electric",
                "Vaporeon,29.0kg,1.0m,0.97,0.25,0.25,Water",
                "Sandshrew,12.0kg,0.6m,0.30,0.25,0.30,Ground",
                "Sandslash,29.5kg,1.0m,0.30,0.56,0.30,Ground",
                "Vulpix,9.9kg,0.6m,0.30,0.25,0.50,Fire",
                "Ninetales,19.9kg,1.1m,0.40,0.80,0.70,Fire",
                "Mewtwo,122.0kg,2.0m,0.95,0.98,0.99,Psychic",
                "Machop,19.5kg,0.8m,0.85,0.75,0.40,Fighting",
                "Machoke,70.5kg,1.5m,0.95,0.80,0.50,Fighting",
                "Machamp,130.0kg,1.6m,1.00,0.95,0.60,Fighting",


        };

        for (String entry : data) {
            String[] fields = entry.split(",");
            pokemons.add(new Pokemon(
                    fields[0],
                    Double.parseDouble(fields[1].replace("kg", "")),
                    Double.parseDouble(fields[2].replace("m", "")),
                    Double.parseDouble(fields[3]),
                    Double.parseDouble(fields[4]),
                    Double.parseDouble(fields[5]),
                    fields[6]
            ));
        }
    }

    public int getSize() {
        return pokemons.size();
    }

    public Pokemon getPokemon(int index) {
        return pokemons.get(index);
    }

    public int searchPokemon(String name) {
        for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
}