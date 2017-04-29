package com.rfong.pokedex.models;

/**
 * Created by rfong on 4/28/17.
 * Abstract Representation of a Pokemon move
 */

public class Move {

    NamePair move; // url and name

    public String getMoveName() {
        return move.getName();
    }
}