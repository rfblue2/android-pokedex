package com.rfong.pokedex.models;

/**
 * Created by rfong on 4/28/17.
 */

public class NamePair {
    String url;
    String name;

    public NamePair(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
