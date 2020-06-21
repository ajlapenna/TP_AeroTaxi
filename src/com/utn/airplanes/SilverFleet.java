package com.utn.airplanes;

import com.utn.enums.EPropulsionType;


public class SilverFleet  extends Airplane implements IExtraServices{

    public SilverFleet(){}

    public SilverFleet(double fullCapacity,
                     int maxPassengerCapacity, double maxVelocity, EPropulsionType ePropulsionType) {
        super(fullCapacity, maxPassengerCapacity, maxVelocity, ePropulsionType);
        super.setFlightFare(flightFare());
    }

    @Override
    public String cateringService() {
        return "Catering service onBoard";
    }

    @Override
    public double flightFare() {
        return 4000;
    }

    public String toString() {
        return this.getClass().getSimpleName()+", "
                +super.toString()+", Extras: "
                +cateringService()+", "+"No wifi onBoard";
    }
}
