package com.utn.airplanes;

import com.utn.enums.EPropulsionType;
import com.utn.tools.*;


public abstract class Airplane {
    private Integer id;
    private double fullCapacity;
    private final double costPerKM = 10.5;
    private int maxPassengerCapacity;
    private double maxVelocity;
    private EPropulsionType EPropulsionType;
    private int countFlights;
    private double flightFare; //(Tarifa del tipo de avión)

    //Constructor//
    public Airplane() {
    }

    public Airplane(double fullCapacity, int maxPassengerCapacity,
                    double maxVelocity, EPropulsionType EPropulsionType) {
        this.id = idHandler.setId();
        this.fullCapacity = fullCapacity;
        this.maxPassengerCapacity = maxPassengerCapacity;
        this.maxVelocity = maxVelocity;
        this.EPropulsionType = EPropulsionType;
        this.countFlights = 0;

    }


    public int getId() {return this.id;}

    public double getFullCapacity() {return fullCapacity;}

    public void setFullCapacity(double fullCapacity) {this.fullCapacity = fullCapacity;}

    public double getCostPerKM() {return costPerKM;}

    public int getMaxPassengerCapacity() {return maxPassengerCapacity;}

    public void setMaxPassengerCapacity(int maxPassengerCapacity) {
        this.maxPassengerCapacity = maxPassengerCapacity;
    }

    public double getMaxVelocity() {return maxVelocity;}

    public void setMaxVelocity(double maxVelocity) {this.maxVelocity = maxVelocity;}

    public String getEPropulsionType() {return EPropulsionType.getEngineType();}

    public void setEPropulsionType(EPropulsionType EPropulsionType) {this.EPropulsionType = EPropulsionType;}

    public int getCountFlights() {return countFlights;}

    //Para que luego podamos hacer un Top de las fleets mas bookeadas
    public void increaseCountFlights() {this.countFlights++;}

    public double getFlightFare(){return this.flightFare;}

    public void setFlightFare(Double flightFare){this.flightFare=flightFare;}

    public abstract double flightFare();


    @Override
    public String toString() {
        return "Id=" + id.toString() +
                ", fullCapacity=" + fullCapacity +
                ", costPerKM=" + costPerKM +
                ", maxPassengerCapacity=" + maxPassengerCapacity +
                ", maxVelocity=" + maxVelocity +
                ", EPropulsionType=" + EPropulsionType;
    }
}
