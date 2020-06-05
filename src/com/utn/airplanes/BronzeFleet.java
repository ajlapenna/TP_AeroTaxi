package com.utn.airplanes;

import com.utn.enums.EPropulsionType;

public class BronzeFleet extends Airplane{

    public BronzeFleet(){}

    public BronzeFleet(double fullCapacity,
                       int maxPassengerCapacity, double maxVelocity, EPropulsionType EPropulsionType) {
        super(fullCapacity, maxPassengerCapacity, maxVelocity, EPropulsionType);
        super.setFlightFare(flightFare());
    }

    @Override
    public double flightFare() {
        return 3000;
    }

    public String toString() {
        return this.getClass().getSimpleName()+", "+super.toString();
    }
}
