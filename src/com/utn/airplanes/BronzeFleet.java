package com.utn.airplanes;

import com.utn.enums.EPropulsionType;

public class BronzeFleet extends Airplane{

    public BronzeFleet(){}

    public BronzeFleet(double fullCapacity,int maxPassengerCapacity,
                       double maxVelocity, EPropulsionType ePropulsionType) {
        super(fullCapacity, maxPassengerCapacity, maxVelocity, ePropulsionType);
        super.setFlightFare(flightFare());
        increaseCountFlights();
    }

    @Override
    public double flightFare() {
        return 3000;
    }

    public String toString() {
        return this.getClass().getSimpleName()+", "+super.toString();
    }
}
