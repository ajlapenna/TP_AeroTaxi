package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.passenger.Passenger;
import com.utn.tools.JsonTools;

import java.time.LocalDate;
import java.util.List;

public class Company {

    private String name;
    private List<Passenger> clients;
    private List<Flight> flights;
    private List<Airplane> airplanes;

    public Company(String name) {
        this.name = name;
        this.clients = JsonTools.readPassengerJson(JsonTools.fpassengers,Passenger.class); //<!-- Tomamos las listas de JSONs
        this.flights = JsonTools.readPassengerJson(JsonTools.fflights,Flight.class);//<!-- Tomamos las listas de JSONs
        this.airplanes = JsonTools.readPassengerJson(JsonTools.fairplanes,Airplane.class);//<!-- Tomamos las listas de JSONs
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
