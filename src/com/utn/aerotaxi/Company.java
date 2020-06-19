package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.passenger.Passenger;
import com.utn.tools.JsonTools;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


public class Company {
    private String name;
    private List<Passenger> passengers;
    private List<Flight> flights;
    private List<Airplane> airplanes;
    private File file;

    public Company(String name) {
        this.name = name;
        setPassengers();
        setFlights();
        setAirplanes();
    }

    ///Comparo la fecha de este momento con la del vuelo -1 día,
    ///ya que no se podran cancelar con menos de 24hs de antelacion
    public boolean unsuscribeFlight(Passenger p, Flight flight) {
        boolean deleted = false;
        if (LocalDate.now().isBefore(flight.getDeparting().plusDays(-1))) {
            deleted=flight.deletePassenger(p);
        } else {
            System.out.println("Debe cancelarse con al menos 24 horas de anticipación");
        }
        return deleted;
    }

    ///Muestro aquellos aviones que corresponden a una fecha enviada por parametro
    public void showFlightsForDate(LocalDate date) {
        System.out.println("Flights to " + date);
        for (Flight f : flights) {
            if (f.getDeparting().isEqual(date))
                System.out.println(f.toString());
        }
    }

    public boolean existPassenger(String dni){
        for (Passenger p : passengers) {
            if(p.getDni().compareToIgnoreCase(dni) == 0)
                return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    private void setPassengers() {
        file = new File(JsonTools.fpassengers);
        if (file.length() == 0)
            passengers = new LinkedList<>();
        else
            passengers = JsonTools.readJson(JsonTools.fpassengers, Passenger.class);
    }

    private void setFlights() {
        file = new File(JsonTools.fflights);
        if (file.length() == 0)
            flights = new LinkedList<>();
        else
            flights = JsonTools.readJson(JsonTools.fflights, Flight.class);
    }

    private void setAirplanes() {
        file = new File(JsonTools.fairplanes);
        if (file.length() == 0)
            airplanes = new LinkedList<>();
        else
            airplanes = JsonTools.readJson(JsonTools.fairplanes, Airplane.class);
    }

    public LinkedList<Passenger> getPassengers() {
        return new LinkedList<>(passengers);
    }

    public LinkedList<Flight> getFlights() {
        return new LinkedList<>(flights);
    }

    public LinkedList<Airplane> getAirplanes() {
        return new LinkedList<>(airplanes);
    }

    public void addPassenger(Passenger passenger) {
        if(passenger != null){
            passengers.add(passenger);
            JsonTools.writeJson(passengers,JsonTools.fpassengers);
        }
    }
}
