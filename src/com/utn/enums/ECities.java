package com.utn.enums;

public enum ECities {
    BSAS("Buenos Aires"),
    CBA("Cordoba"),
    SANTCHILE("Santiago de Chile"),
    MONTVIDE("Montevideo");

    private String cityName;

    ECities(String cityName){this.cityName=cityName;}

    public String getCityName(){return this.cityName;}
}
