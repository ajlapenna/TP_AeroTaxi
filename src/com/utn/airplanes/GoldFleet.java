package com.utn.airplanes;

import com.utn.enums.*;
import java.util.Random;


public class GoldFleet extends Airplane implements IExtraServices {

    private boolean wifiOnBoard;


    public GoldFleet() {
    }

    public GoldFleet(double fullCapacity,
                     int maxPassengerCapacity, double maxVelocity, EPropulsionType ePropulsionType) {
        super(fullCapacity, maxPassengerCapacity, maxVelocity, ePropulsionType);
        super.setFlightFare(flightFare());
        this.wifiOnBoard = wifiOnBoard();
    }

    @Override
    public double flightFare() {
        return 6000;
    }


    @Override
    public String cateringService() {
        return "Catering service onBoard";
    }

    private boolean wifiOnBoard() {
        return new Random().nextBoolean();
    }

    public boolean isWifiOnBoard(){return wifiOnBoard;}


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", "
                + super.toString() + ", Extras: "
                + cateringService() + ", " +
                ((isWifiOnBoard()) ? "Wifi onBoard" : "No wifi onBoard");
    }
}
