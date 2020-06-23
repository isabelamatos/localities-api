package com.localities.ibge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class IbgeCity {
    private int id;
    private String name;
    private IbgeMicroregion ibgeMicroregion;

    @JsonCreator
    public IbgeCity(int id, String nome, IbgeMicroregion microrregiao) {
        this.id = id;
        this.name = nome;
        this.ibgeMicroregion = microrregiao;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public IbgeMicroregion getIbgeMicroregion() {
        return ibgeMicroregion;
    }

    public void setIbgeMicroregion(final IbgeMicroregion ibgeMicroregion) {
        this.ibgeMicroregion = ibgeMicroregion;
    }
}