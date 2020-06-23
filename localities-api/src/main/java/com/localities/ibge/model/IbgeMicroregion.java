package com.localities.ibge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class IbgeMicroregion {
    private int id;
    private String name;
    private IbgeMesoregion ibgeMesoregion;

    @JsonCreator
    public IbgeMicroregion(int id, String nome, IbgeMesoregion mesorregiao) {
        this.id = id;
        this.name = nome;
        this.ibgeMesoregion = mesorregiao;
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

    public IbgeMesoregion getIbgeMesoregion() {
        return ibgeMesoregion;
    }

    public void setIbgeMesoregion(IbgeMesoregion ibgeMesoregion) {
        this.ibgeMesoregion = ibgeMesoregion;
    }
}