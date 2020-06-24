package com.utn.person;

import com.utn.aerotaxi.Flight;
import com.utn.airplanes.BronzeFleet;
import com.utn.airplanes.GoldFleet;
import com.utn.airplanes.SilverFleet;

import java.util.ArrayList;
import java.util.Objects;

public class Passenger extends Person {
    private double totalSpend;
    private String bestAirplane;
    private ArrayList<Flight> flights;

    public Passenger() {}
    public Passenger(String name, String lastName, String dni, int age, String password) {
        super(name, lastName, dni, age, password);
        totalSpend = 0;
        bestAirplane = null;
        flights = new ArrayList<>();
    }


    public double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public String getBestAirplane() {
        return bestAirplane;
    }

    public void setBestAirplane() {
        if (!flights.isEmpty()) {
            for (Flight f : flights) {
                if (f.getAirplane() instanceof BronzeFleet)
                    bestAirplane = "Bronze";
                else if (f.getAirplane() instanceof SilverFleet)
                    bestAirplane = "Silver";
                else if (f.getAirplane() instanceof GoldFleet)
                    bestAirplane = "Gold";
            }
        }
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(getDni(), passenger.getDni()) &&
                Objects.equals(getLastName(), passenger.getLastName()) &&
                Objects.equals(getName(), passenger.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni(), getLastName(), getName());
    }

    @Override
    public String toString() {
        return super.toString() +
                " totalSpend=" + totalSpend +
                ", bestAirplane='" + bestAirplane + '\'' +
                ", flights=" + flights.toString();
    }
}
