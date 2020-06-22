package com.utn.airplanes;

import com.utn.enums.EPropulsionType;

public class BronzeFleet extends Airplane {
    public BronzeFleet() {}

    public BronzeFleet(double fuelCapacity, int maxPassengerCapacity, double topSpeed,
                       EPropulsionType ePropulsionType) {
        super(fuelCapacity, maxPassengerCapacity, topSpeed, ePropulsionType);
        super.setFlightFare(3000);
    }

    public String toString() {
        return this.getClass().getSimpleName()+ ", "+ super.toString();
    }
}
