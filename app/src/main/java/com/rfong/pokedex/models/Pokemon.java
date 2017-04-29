package com.rfong.pokedex.models;

import java.util.List;

/**
 * Created by rfong on 4/28/17.
 * An abstract representation of a pokemon
 */
public class Pokemon {

    Integer id;
    String name;
    SpriteList sprites;
    List<Stat> stats;
    List<Type> types;
    List<Move> moves;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public List<Type> getTypes() {
        return types;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public SpriteList getSprites() {
        return sprites;
    }

    /**
     * Specifically returns front_default sprite URL
     * @return
     */
    public String getSpriteUrl() {
        return sprites.getFront_default();
    }
}
