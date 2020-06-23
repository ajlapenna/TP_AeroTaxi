package com.utn.airplanes;

import com.utn.enums.EPropulsionType;

import java.util.Random;

public class GoldFleet extends Airplane implements IExtraServices {
    private boolean wifiOnBoard;

    public GoldFleet() {
        super(1000, 5, 700, EPropulsionType.REACTION, 6000);
        this.wifiOnBoard = new Random().nextBoolean();
    }

    @Override
    public String cateringService() {
        return "Catering service onBoard";
    }

    public boolean isWifiOnBoard() {
        return wifiOnBoard;
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", "
                + super.toString() + ", Extras: "
                + cateringService() + ", " +
                ((isWifiOnBoard()) ? "Wifi onBoard" : "No wifi onBoard");
    }
}
