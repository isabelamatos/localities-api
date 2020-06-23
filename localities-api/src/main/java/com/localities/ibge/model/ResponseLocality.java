package com.localities.ibge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseLocality {
    private int stateId;
    private String stateAbbreviation;
    private String regionName;
    private String cityName;
    private String mesoregionName;

    public ResponseLocality(int stateId, String stateAbbreviation, String regionName, String cityName, String mesoregionName) {
        this.stateId = stateId;
        this.stateAbbreviation = stateAbbreviation;
        this.regionName = regionName;
        this.cityName = cityName;
        this.mesoregionName = mesoregionName;
    }

    public static String[] getFileFields() {
        return new String[] { "idEstado", "siglaEstado", "regiaoNome", "nomeCidade", "nomeMesorregiao", "nomeFormatado" };
    }

    @JsonProperty("idEstado")
    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @JsonProperty("siglaEstado")
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    @JsonProperty("regiaoNome")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @JsonProperty("nomeCidade")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @JsonProperty("nomeMesorregiao")
    public String getMesoregionName() {
        return mesoregionName;
    }

    public void setMesoregionName(String mesoregionName) {
        this.mesoregionName = mesoregionName;
    }

    @JsonProperty("nomeFormatado")
    public String getFormattedName() {
        return cityName + "/" + stateAbbreviation;
    }
}
