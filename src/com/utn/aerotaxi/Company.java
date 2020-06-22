package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.person.Admin;
import com.utn.person.Passenger;
import com.utn.person.Person;
import com.utn.tools.JsonTools;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Company {
    private String name;
    private List<Passenger> passengers;
    private List<Admin> admins;
    private List<Flight> flights;
    private List<Airplane> airplanes;
    private File file;

    public Company(String name) {
        this.name = name;
        setPassengers();
        setAdmins();
        setFlights();
        setAirplanes();
        file = null;
    }

    public boolean existsPassenger(String dni){
        for (Passenger p : passengers) {
            if(p.getDni().compareToIgnoreCase(dni) == 0)
                return true;
        }
        return false;
    }

    public boolean existsAirplane(String id) {
        for (Airplane a : airplanes) {
            if (id.compareToIgnoreCase(a.getId()) == 0)
                return true;
        }
        return false;
    }

    private void setPassengers() {
        file = new File(JsonTools.fpassengers);
        if (file.length() == 0)
            passengers = new LinkedList<>();
        else
            passengers = JsonTools.readJson(JsonTools.fpassengers, Passenger.class);
    }

    private void setAdmins() {
        file = new File(JsonTools.fadmins);
        if (file.length() == 0)
            admins = new LinkedList<>();
        else
            admins = JsonTools.readJson(JsonTools.fadmins, Admin.class);
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

    public void addUser(Person user) {
        if(user != null){
            if (user instanceof Passenger) {
                passengers.add((Passenger) user);
                JsonTools.writeJson(passengers, JsonTools.fpassengers);
            } else if (user instanceof Admin) {
                admins.add((Admin) user);
                JsonTools.writeJson(admins, JsonTools.fadmins);
            }
        }
    }

    public LinkedList<Admin> getAdmins() {
        return new LinkedList<>(admins);
    }

    public String getName() {
        return name;
    }
}
