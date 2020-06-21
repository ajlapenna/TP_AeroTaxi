package com.utn.airplanes;

import com.utn.enums.ECities;
import com.utn.enums.EPropulsionType;
import com.utn.tools.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public abstract class Airplane implements Serializable {
    private String id;
    private double fuelCapacity;
    private double costPerKM = Toolbox.costRandom(); //Random 150 to 300
    private int maxPassengerCapacity;
    private double topSpeed;
    private EPropulsionType EPropulsionType;
    private int countFlights;
    private double flightFare; //(Tarifa del tipo de avión)
    private LocalDate nextDepartingDate;
    private ECities nextCity;


    //Constructor//
    public Airplane() {
    }

    public Airplane(double fuelCapacity, int maxPassengerCapacity,
                    double topSpeed, EPropulsionType EPropulsionType) {
        this.id = Toolbox.setId();
        this.fuelCapacity = fuelCapacity;
        this.maxPassengerCapacity = maxPassengerCapacity;
        this.topSpeed = topSpeed;
        this.EPropulsionType = EPropulsionType;
        this.countFlights = 0;
        this.nextDepartingDate = LocalDate.of(1,1,1);
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

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public double getCostPerKM() {
        return costPerKM;
    }

    public int getMaxPassengerCapacity() {
        return maxPassengerCapacity;
    }

    public void setMaxPassengerCapacity(int maxPassengerCapacity) {
        this.maxPassengerCapacity = maxPassengerCapacity;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getEPropulsionType() {
        return EPropulsionType.getEngineType();
    }

    public void setEPropulsionType(EPropulsionType EPropulsionType) {
        this.EPropulsionType = EPropulsionType;
    }

    public int getCountFlights() {
        return countFlights;
    }

    //Para que podamos hacer un Top de las fleets mas bookeadas
    public void increaseCountFlights() {
        this.countFlights++;
    }

    public double getFlightFare() {
        return this.flightFare;
    }

    public void setFlightFare(Double flightFare) {
        this.flightFare = flightFare;
    }

    public abstract double flightFare();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return Double.compare(airplane.fuelCapacity, fuelCapacity) == 0 &&
                Double.compare(airplane.costPerKM, costPerKM) == 0 &&
                maxPassengerCapacity == airplane.maxPassengerCapacity &&
                Double.compare(airplane.topSpeed, topSpeed) == 0 &&
                countFlights == airplane.countFlights &&
                Double.compare(airplane.flightFare, flightFare) == 0 &&
                Objects.equals(id, airplane.id) &&
                EPropulsionType == airplane.EPropulsionType &&
                Objects.equals(nextDepartingDate, airplane.nextDepartingDate) &&
                nextCity == airplane.nextCity;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, fuelCapacity, costPerKM, maxPassengerCapacity, topSpeed, EPropulsionType, countFlights, flightFare, nextDepartingDate, nextCity);
    }


    @Override
    public String toString() {
        return "Id=" + id +
                ", fullCapacity=" + fuelCapacity +
                ", costPerKM=" + costPerKM +
                ", passengerCapacity=" + maxPassengerCapacity +
                ", topSpeed=" + topSpeed +
                ", propulsionType=" + EPropulsionType;
    }
}
