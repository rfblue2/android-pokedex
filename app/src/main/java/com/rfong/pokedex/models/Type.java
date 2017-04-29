package com.rfong.pokedex.models;

/**
 * Created by rfong on 4/28/17.
 * Abstract Representation of a type
 */

public class Type {
    NamePair type; // url and name

    public String getName() {
        return type.getName();
    }

}