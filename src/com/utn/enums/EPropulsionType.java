package com.utn.enums;

public enum EPropulsionType {
    REACTION("Reaction Engine"), PROPELLER("Propeller Engine"), PISTON("Piston Engine");


    private String engineType;

    EPropulsionType(String engineType){this.engineType=engineType;}

    public String getEngineType(){return this.engineType;}
}
