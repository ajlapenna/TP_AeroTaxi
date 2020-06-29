package com.utn.airplanes;

import com.utn.enums.EPropulsionType;

public class BronzeFleet extends Airplane {

    public BronzeFleet() {
        super(1200, 10, 450, EPropulsionType.PROPELLER, 3000);
    }

    public String toString() {
        return this.getClass().getSimpleName()+ ", "+ super.toString();
    }
}
