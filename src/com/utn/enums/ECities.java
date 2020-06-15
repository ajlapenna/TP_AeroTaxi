package com.utn.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ECities implements Serializable {
    BSAS("Buenos Aires"),
    CBA("Cordoba"),
    SANTCHILE("Santiago de Chile"),
    MONTVIDE("Montevideo");

    private String cityName;

    ECities(String cityName){this.cityName=cityName;}

    @JsonValue
    public String getCityName(){return this.cityName;}
}
