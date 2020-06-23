package com.utn.airplanes;

import com.utn.enums.EPropulsionType;

public class SilverFleet extends Airplane implements IExtraServices {

    public SilverFleet() {
        super(1000, 7, 550, EPropulsionType.PISTON, 4000);
    }

    @Override
    public String cateringService() {
        return "Catering service onBoard";
    }

    public String toString() {
        return this.getClass().getSimpleName()+ ", "
                + super.toString()+ ", Extras: "
                + cateringService()+ ", " + "No wifi onBoard";
    }
}
