package com.rfong.pokedex.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rfong on 4/28/17.
 * Holds result of GET /pokemon
 */

public class PokemonList {
    List<NamePair> results;

    /**
     * Returns a list of names of the pokemon
     * @return
     */
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (NamePair pair : results) names.add(pair.getName());

        return names;
    }

    /**
     * Returns the namepairs
     * @return
     */
    public List<NamePair> getNamePairs() {
        return results;
    }
}
