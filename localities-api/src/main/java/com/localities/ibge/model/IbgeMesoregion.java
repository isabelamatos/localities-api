package com.localities.ibge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class IbgeMesoregion {
    private int id;
    private String name;
    private IbgeState ibgeState;


    @JsonCreator
    public IbgeMesoregion(int id, String nome, IbgeState UF) {
        this.id = id;
        this.name = nome;
        this.ibgeState = UF;
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

    public IbgeState getIbgeState() {
        return ibgeState;
    }

    public void setIbgeState(final IbgeState ibgeState) {
        this.ibgeState = ibgeState;
    }
}
