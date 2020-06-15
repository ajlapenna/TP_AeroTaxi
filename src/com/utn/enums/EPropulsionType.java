package com.utn.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EPropulsionType implements Serializable {
    REACTION("Reaction Engine"), PROPELLER("Propeller Engine"), PISTON("Piston Engine");

    private String engineType;

    private EPropulsionType(String engineType){this.engineType=engineType;}

    @JsonValue
    public String getEngineType(){return this.engineType;}
}
