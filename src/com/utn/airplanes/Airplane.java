package com.utn.airplanes;

import com.utn.enums.ECities;
import com.utn.enums.EPropulsionType;
import com.utn.tools.Toolbox;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Airplane implements Serializable {
    private String id;
    private double fuelCapacity;
    private double costPerKM; //Random 150 to 300
    private int maxPassengerCapacity;
    private double topSpeed;
    private EPropulsionType ePropulsionType;
    private int countFlights;
    private int flightFare; //(Tarifa del tipo de avión)
    private LocalDate nextDepartingDate;
    private ECities nextCity;

    public Airplane() {
    }

    public Airplane(double fuelCapacity, int maxPassengerCapacity, double topSpeed, EPropulsionType ePropulsionType, int flightFare) {
        this.id = Toolbox.setId();
        this.fuelCapacity = fuelCapacity;
        this.costPerKM = Toolbox.costRandom();
        this.maxPassengerCapacity = maxPassengerCapacity;
        this.topSpeed = topSpeed;
        this.ePropulsionType = ePropulsionType;
        this.countFlights = 0;
        this.nextDepartingDate= LocalDate.of(1,1,1);
    }

    public void setNextCity(ECities nextCity) {
        this.nextCity = nextCity;
    }

    public void setNextDepartingDate(LocalDate nextDeparting) {
        this.nextDepartingDate = nextDeparting;
    }

    public ECities getNextCity() {
        return nextCity;
    }

    public LocalDate getNextDepartingDate() {
        return nextDepartingDate;
    }

    public String getId() {
        return this.id;
    }

    public double getFuelCapacity() {return fuelCapacity;}

    public void setFuelCapacity(double fuelCapacity) {this.fuelCapacity = fuelCapacity;}

    public double getCostPerKM() {return costPerKM;}

    public int getMaxPassengerCapacity() {return maxPassengerCapacity;}

    public void setMaxPassengerCapacity(int maxPassengerCapacity) {
        this.maxPassengerCapacity = maxPassengerCapacity;
    }

    public double getTopSpeed() {return topSpeed;}

    public void setTopSpeed(double topSpeed) {this.topSpeed = topSpeed;}

    public String getePropulsionType() {return ePropulsionType.getEngineType();}

    public void setePropulsionType(EPropulsionType ePropulsionType) {this.ePropulsionType = ePropulsionType;}

    public int getCountFlights() {return countFlights;}

    //Para que podamos hacer un Top de las fleets mas bookeadas
    public void increaseCountFlights() {this.countFlights++;}

    public double getFlightFare(){return this.flightFare;}

    public void setFlightFare(int flightFare){this.flightFare=flightFare;}

    @Override
    public String toString() {
        return "Id=" + id+
                ", fullCapacity=" + fuelCapacity +
                ", costPerKM=" + costPerKM +
                ", passengerCapacity=" + maxPassengerCapacity +
                ", topSpeed=" + topSpeed +
                ", propulsionType=" + ePropulsionType;
    }


}
