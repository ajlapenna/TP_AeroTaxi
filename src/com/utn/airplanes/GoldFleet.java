package com.utn.airplanes;

import com.utn.enums.EPropulsionType;

import java.util.Random;

public class GoldFleet extends Airplane implements IExtraServices {
    private boolean wifiOnBoard;

    public GoldFleet() {
    }

    public GoldFleet(double fuelCapacity, int maxPassengerCapacity, double topSpeed,
                     EPropulsionType ePropulsionType) {
        super(fuelCapacity, maxPassengerCapacity, topSpeed, ePropulsionType);
        super.setFlightFare(6000);
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
