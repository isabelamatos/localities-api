package com.localities.ibge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class IbgeState {
    private int id;
    private String name;
    private String abbreviation;
    private IbgeRegion ibgeRegion;

    @JsonCreator
    public IbgeState(int id, String nome, String sigla, IbgeRegion regiao) {
        this.id = id;
        this.name = nome;
        this.abbreviation = sigla;
        this.ibgeRegion = regiao;
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

    public IbgeRegion getIbgeRegion() {
        return ibgeRegion;
    }

    public void setIbgeRegion(IbgeRegion ibgeRegion) {
        this.ibgeRegion = ibgeRegion;
    }
}
