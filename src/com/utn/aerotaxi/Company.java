package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.passenger.Passenger;

import java.time.LocalDate;
import java.util.LinkedList;

public class Company {

    private String name;
    private LinkedList<Passenger> clients;
    private LinkedList<Flight> flights;
    private LinkedList<Airplane> airplanes;

    public Company(String name, LinkedList<Passenger> clients, LinkedList<Flight> flights,
                   LinkedList<Airplane> airplanes) {

        this.name = name;
        this.clients = clients;
        this.flights = flights;
        this.airplanes = airplanes;
    }

    ///Muestro aquellos aviones que corresponden a una fecha enviada por parametro
    public void showFlightsForDate(LocalDate date) {
        System.out.println("Flights to "+date);
        for (Flight f : flights) {
            if(f.getDeparting().isEqual(date) == true)
                System.out.println(f.toString());
        }
    }


}
