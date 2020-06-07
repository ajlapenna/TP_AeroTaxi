package com.utn.airplanes;

import com.utn.enums.EPropulsionType;
import com.utn.tools.*;
import java.io.Serializable;

public abstract class Airplane implements Serializable {
    private String id;
    private double fuelCapacity;
    private double costPerKM=toolbox.costRandom(); //Random 150 to 300
    private int maxPassengerCapacity;
    private double topSpeed;
    private EPropulsionType EPropulsionType;
    private int countFlights;
    private double flightFare; //(Tarifa del tipo de avión)

    //Constructor//
    public Airplane() {
    }

    public Airplane(double fuelCapacity, int maxPassengerCapacity,
                    double topSpeed, EPropulsionType EPropulsionType) {
        this.id = toolbox.setId();
        this.fuelCapacity = fuelCapacity;
        this.maxPassengerCapacity = maxPassengerCapacity;
        this.topSpeed = topSpeed;
        this.EPropulsionType = EPropulsionType;
        this.countFlights = 0;

    }


    public String getId() {return this.id;}

    public double getFuelCapacity() {return fuelCapacity;}

    public void setFuelCapacity(double fuelCapacity) {this.fuelCapacity = fuelCapacity;}

    public double getCostPerKM() {return costPerKM;}

    public int getMaxPassengerCapacity() {return maxPassengerCapacity;}

    public void setMaxPassengerCapacity(int maxPassengerCapacity) {
        this.maxPassengerCapacity = maxPassengerCapacity;
    }

    public double getTopSpeed() {return topSpeed;}

    public void setTopSpeed(double topSpeed) {this.topSpeed = topSpeed;}

    public String getEPropulsionType() {return EPropulsionType.getEngineType();}

    public void setEPropulsionType(EPropulsionType EPropulsionType) {this.EPropulsionType = EPropulsionType;}

    public int getCountFlights() {return countFlights;}

    //Para que podamos hacer un Top de las fleets mas bookeadas
    public void increaseCountFlights() {this.countFlights++;}

    public double getFlightFare(){return this.flightFare;}

    public void setFlightFare(Double flightFare){this.flightFare=flightFare;}

    public abstract double flightFare();


    @Override
    public String toString() {
        return "Id=" + id+
                ", fullCapacity=" + fuelCapacity +
                ", costPerKM=" + costPerKM +
                ", passengerCapacity=" + maxPassengerCapacity +
                ", topSpeed=" + topSpeed +
                ", propulsionType=" + EPropulsionType;
    }
}
