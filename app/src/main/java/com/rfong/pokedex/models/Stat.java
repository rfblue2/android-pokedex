package com.rfong.pokedex.models;

/**
 * Created by rfong on 4/28/17.
 * Abstract representation of a Pokemon stat
 */

public class Stat {
    NamePair stat; // url and name
    Integer base_stat;

    public String getStateName() {
        return stat.getName();
    }

    public Integer getBase_stat() {
        return base_stat;
    }

}