package com.utn.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EDistance implements Serializable {

    BsAsCordoba(ECities.BSAS,ECities.CBA,695),
    BsAsSantiago(ECities.BSAS,ECities.SANTCHILE,1400),
    BsAsMontevideo(ECities.BSAS,ECities.MONTVIDE,950),
    CordobaMontevideo(ECities.CBA,ECities.MONTVIDE,1190),
    CordobaSantiago(ECities.CBA,ECities.SANTCHILE,1050),
    MontevideoSantiago(ECities.MONTVIDE,ECities.SANTCHILE,2100);

    private ECities origen;
    private ECities destino;
    private int distance;

    EDistance(ECities origen,ECities destino,int distance){
        this.origen = origen;
        this.destino = destino;
        this.distance = distance;
    }

    public ECities getDestino() {
        return destino;
    }

    public ECities getOrigen() {
        return origen;
    }

    public int getDistance() {
        return distance;
    }
}
