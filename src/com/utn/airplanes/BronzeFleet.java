package com.utn.airplanes;

import com.utn.enums.EPropulsionType;

public class BronzeFleet extends Airplane{

    public BronzeFleet(){}

    public BronzeFleet(double fullCapacity,
                       int maxPassengerCapacity, double maxVelocity, com.utn.enums.EPropulsionType EPropulsionType) {
        super(fullCapacity, maxPassengerCapacity, maxVelocity, EPropulsionType);
        super.setFlightFare(flightFare());
    }

    @Override
    public double flightFare() {
        return 500;
    }

    public String toString() {
        return this.getClass().getSimpleName()+", "+super.toString();
    }
}
