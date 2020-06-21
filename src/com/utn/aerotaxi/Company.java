package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.enums.ECities;
import com.utn.passenger.Passenger;
import com.utn.tools.JsonTools;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


public class Company {

    private String name;
    private List<Passenger> clients;
    private List<Flight> flights;
    private List<Airplane> airplanes;

    public Company(String name) {
        this.name = name;
        this.clients = JsonTools.readJson(JsonTools.fpassengers, Passenger.class); //<!-- Tomamos las listas de JSONs
        this.flights = JsonTools.readJson(JsonTools.fflights, Flight.class);//<!-- Tomamos las listas de JSONs
        this.airplanes = JsonTools.readJson(JsonTools.fairplanes, Airplane.class);//<!-- Tomamos las listas de JSONs
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

    ///Muestro aquellos aviones que corresponden a una fecha enviada por parametro
    public void showFlightsForDate(LocalDate date) {
        System.out.println("Flights to " + date);
        for (Flight f : flights) {
            if (f.getDeparting().isEqual(date) == true)
                System.out.println(f.toString());
        }
    }

    public boolean existFlightForDepartingDate(LocalDate departingDate) {
        boolean rta = false;
        for (Flight f : flights) {
            if (f.getDeparting().isEqual(departingDate) == true)
                rta = true;
        }
        return rta;
    }

    public boolean existClient(String dni) {
        boolean rta = false;
        for (Passenger p : this.clients) {
            if (p.getDni().compareToIgnoreCase(dni) == 0)
                rta = true;
        }
        return rta;
    }

    public void addClient(Passenger newClient) {
        if (newClient instanceof Passenger)
            clients.add(newClient);
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

    public boolean existAirplane(String id) {
        boolean rta = false;
        for (Airplane a : airplanes) {
            if (id.compareToIgnoreCase(a.getId()) == 0)
                rta = true;
        }
        return rta;
    }

    public Airplane searchAirplanForId(String id) {
        Airplane toSearch = null;
        for (Airplane a : airplanes) {
            if (id.compareToIgnoreCase(a.getId()) == 0)
                toSearch = a;
        }
        return toSearch;
    }


    ///Para buscar el vuelo correspondiente a un avion, tambien necesitamos la fecha de partida del vuelo
    public Flight searchFlightForAirplaneAndDate(Airplane a, LocalDate departingDate) {
        Flight flightToSearch = null;
        for (Flight f : flights) {
            if (f.getAirplane().equals(a) == true && departingDate.isEqual(f.getDeparting()) == true) {
                flightToSearch = f;
            }
        }
        return flightToSearch;
    }

    public void showAvaibleFlights(int countOfPassengers, ECities departureCity, LocalDate departingDate) {

        for (Airplane a : airplanes) {
            //Si su proximo vuelo se realiza otro día, lo muestro
            if (!a.getNextDepartingDate().isEqual(departingDate)) {
                System.out.println(a.toString());
                // Si su proximo vuelo es el mismo dia, evaluamos la ciudad de destino
            } else if (a.getNextCity() == departureCity) {
                //Si es distanta no la buscamos, ya que cada avion solo puede hacer un destino por dia
                //Si es igual,buscamos el flight registrado en la lista y comprobamos que su capacidad sea suficiente
                if (searchFlightForAirplaneAndDate(a, departingDate).getPassengers().size() <= countOfPassengers) {
                    System.out.println(a);
                }

            }
        }

    }


    public String getName() {
        return name;
    }

    public LinkedList<Passenger> getClients() {
        return new LinkedList<>(clients);
    }

    public LinkedList<Flight> getFlights() {
        return new LinkedList<>(flights);
    }

    public LinkedList<Airplane> getAirplanes() {
        return new LinkedList<>(airplanes);
    }

    public void addPassenger(Passenger client) {
        if (client != null) {
            clients.add(client);
            JsonTools.writeJson(clients, JsonTools.fpassengers);
        }
    }
}
