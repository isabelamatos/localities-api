package com.localities.ibge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class IbgeRegion {
    private int id;
    private String name;
    private String abbreviation;

    @JsonCreator
    public IbgeRegion(int id, String nome, String sigla) {
        this.id = id;
        this.name = nome;
        this.abbreviation = sigla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
