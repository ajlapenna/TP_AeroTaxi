package com.utn.passenger;

import com.utn.aerotaxi.Flight;

import java.io.Serializable;
import java.util.ArrayList;


public class Passenger extends Person implements Serializable {

    private double totalSpend;
    private String bestAirplane;
    private ArrayList<Flight> flights= new ArrayList<>();

    //Constructor//
    public Passenger(){}

    public Passenger(String name, String lastName, String dni, int age) {
        super(name, lastName, dni, age);
    }

    public double getTotalSpend(){return this.totalSpend;}

    public boolean addFlights(Flight flight){return this.flights.add(flight);}

    public void setTotalSpend(double totalSpend){this.totalSpend=totalSpend;}

    public ArrayList<Flight> getFlights(){return flights;}

    public String getBestAirplane(){/*TODO*/return this.bestAirplane;}

    public void setBestAirplane(String bestAirplane){/*TODO*/}

    @Override
    public String toString() {return super.toString()
            +((bestAirplane!=null)? ", Total spend=$" + totalSpend + ", bestAirplane=" + bestAirplane:
            ", Without flights.");}
}
