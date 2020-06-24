package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.enums.ECities;
import com.utn.person.Admin;
import com.utn.person.Passenger;
import com.utn.person.Person;
import com.utn.tools.JsonTools;

import java.io.File;
import java.time.LocalDate;
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

    ///Comparo la fecha de este momento con la del vuelo -1 día,
    ///ya que no se podran cancelar con menos de 24hs de antelacion
    public boolean unsuscribeFlight(Passenger p, Flight flight) {
        boolean deleted = false;
        if (LocalDate.now().isBefore(flight.getDeparting().plusDays(-1))) {
            deleted = flight.deletePassenger(p);
        } else {
            System.out.println("Debe cancelarse con al menos 24 horas de anticipación");
        }
        return deleted;
    }

    public boolean existsPassenger(String dni) {
        for (Passenger p : passengers) {
            if (p.getDni().compareToIgnoreCase(dni) == 0)
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

    public void addFlight(Flight toAdd) {
        if (toAdd != null)
            flights.add(toAdd);
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
        if (user != null) {
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

    public Flight searchFlightForAirplaneAndDate(Airplane a, LocalDate departingDate) {
        Flight flightToSearch = null;
        if (flights != null) {
            for (Flight f : flights) {
                if (f.getAirplane().equals(a) && departingDate.isEqual(f.getDeparting())) {
                    flightToSearch = f;
                }
            }
        }
        return flightToSearch;
    }

    public String showAllFlightsByDay(LocalDate departingDate) {
        StringBuilder flightOftheDay = new StringBuilder();

        for (Flight f : flights) {
            if (departingDate.isEqual(f.getDeparting())) {
                flightOftheDay.append(f.toString());
                flightOftheDay.append("\n");
            }
        }
        return flightOftheDay.toString();
    }

    public boolean showAvailableAirplanes(int countOfPassengers, ECities departureCity, LocalDate departingDate) {
        boolean flag = false;// Esta bandera se utiliza para saber si aunquesea se muestra un avion, caso contrario imprime mensaje
        for (Airplane a : airplanes) {
            //Primero que nada evalúo la capacidad del avion
            if (a.getMaxPassengerCapacity() >= countOfPassengers) {
                //Si su proximo vuelo se realiza otro día, lo muestro
                if (!a.getNextDepartingDate().isEqual(departingDate)) {
                    System.out.println(a.toString());
                    flag = true;
                    // Si su proximo vuelo es el mismo dia, evaluamos la ciudad de destino
                } else if (a.getNextCity() == departureCity) {
                    //Si es distanta no lo mostramos, ya que cada avion solo puede hacer un destino por dia
                    System.out.println(a);
                    flag = true;
                }
            }
        }
        return flag;
    }

    public String showAllFlights() {
        StringBuilder showFlights = new StringBuilder();
        for (Flight currentFlight : flights) {
            showFlights.append(currentFlight.toString());
            showFlights.append("\n");
        }
        return showFlights.toString();
    }

    public boolean existAirplane(String id) {
        boolean rta = false;
        for (Airplane a : airplanes) {
            if (id.compareToIgnoreCase(a.getId()) == 0)
                rta = true;
        }
        return rta;
    }

    public double calculateTicketCostForAirplanId(String id, int countOfPassengers, int flightDistance) {
        double ticketCost = 0;
        for (Airplane toSearch : airplanes) {
            if (toSearch.getId().compareToIgnoreCase(id) == 0) {
                ticketCost = (toSearch.getCostPerKM() * flightDistance) +
                        (countOfPassengers * 3500) + toSearch.getFlightFare();
            }
        }
        return ticketCost;
    }

    public Airplane searchAirplaneForId(String id) {
        Airplane toSearch = null;
        for (Airplane a : airplanes) {
            if (id.compareToIgnoreCase(a.getId()) == 0)
                toSearch = a;
        }
        return toSearch;
    }

    public String showAllPassengers() {
        if (passengers.isEmpty()) return "E R R O R empty list of passengers";
        else {
            StringBuilder stringPassengers = new StringBuilder();
            for (int i = 0; i < passengers.size(); i++) {
                Passenger passenger = passengers.get(i);
                stringPassengers.append(i);
                stringPassengers.append(" ");
                stringPassengers.append(passenger.toString());
                stringPassengers.append("\n");
            }
            return stringPassengers.toString();
        }
    }
}
