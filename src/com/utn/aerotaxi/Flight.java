package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.enums.ECities;
import com.utn.passenger.Passenger;
import com.utn.tools.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;


public class Flight implements Serializable {

    private Airplane airplane;
    private String id;
    private ECities departureCity;
    private ECities arrivalCity;
    private int distance;
    private LocalDate departing;
    private LinkedList<Passenger> passengers = new LinkedList<>();   ///<!--los tomamos desde los tickets
    private LinkedList<FlightTicket> flightTickets = new LinkedList<>();
    private static int countOfPassengers = 1;
    private boolean isGone;///<!--- Despego?

    public Flight() {
    }

    public Flight(Airplane airplane, ECities departureCity,
                  ECities arrivalCity, LocalDate departing) {
        if (airplane != null) {
            this.id = Toolbox.setId();
            this.airplane = airplane;
            this.departureCity = departureCity;
            this.arrivalCity = arrivalCity;
            this.departing = departing;
            this.distance = setDistance();
            this.isGone = false;
        }
    }

    private int setDistance() {
        return ((departureCity == ECities.BSAS && arrivalCity == ECities.CBA) ||
                (departureCity == ECities.CBA && arrivalCity == ECities.BSAS)) ? 695 :
                ((departureCity == ECities.BSAS && arrivalCity == ECities.SANTCHILE) ||
                        (departureCity == ECities.SANTCHILE && arrivalCity == ECities.BSAS)) ? 1400 :
                        ((departureCity == ECities.BSAS && arrivalCity == ECities.MONTVIDE) ||
                                (departureCity == ECities.MONTVIDE && arrivalCity == ECities.BSAS)) ? 950 :
                                ((departureCity == ECities.CBA && arrivalCity == ECities.MONTVIDE) ||
                                        (departureCity == ECities.MONTVIDE && arrivalCity == ECities.CBA)) ? 1190 :
                                        ((departureCity == ECities.CBA && arrivalCity == ECities.SANTCHILE) ||
                                                (departureCity == ECities.SANTCHILE && arrivalCity == ECities.CBA)) ? 1050 :
                                                ((departureCity == ECities.MONTVIDE && arrivalCity == ECities.SANTCHILE) ||
                                                        (departureCity == ECities.SANTCHILE && arrivalCity == ECities.MONTVIDE)) ? 2100 : 0;
    }

    public int getDistance() {
        return this.distance;
    }

    public Airplane getAirplane() {
        return this.airplane;
    }

    public LinkedList<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Add a flight ticket if the amount of passengers from the ticket is less
     * than the maximum capacity of the airplane.
     *
     * @param ticket = Current ticket
     * @return TRUE = flight ticket added.
     * FALSE = flight ticket doesn't added
     */
    public boolean addFlightTicket(FlightTicket ticket) {
        if (ticket == null) {
            return false;
        } else {
            if (ticket.getNumberOfPassengers() + countOfPassengers < airplane.getMaxPassengerCapacity()) {
                flightTickets.add(ticket);
                passengers.add(ticket.getMainPassenger());
                return true;
            } else return false;
        }
    }

    public boolean deletePassenger(Passenger toDelete) {
        boolean result = false;
        for (Passenger p : passengers) {
            if (p.equals(toDelete)) {
                p.setDeleted(true);
                result = true;
            }
        }
        return result;
    }

    public LocalDate getDeparting() {
        return this.departing;
    }

    public LinkedList<FlightTicket> getFlightTickets() {
        return flightTickets;
    }

    public ECities getDepartureCity() {
        return departureCity;
    }

    public ECities getArrivalCity() {
        return arrivalCity;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", airplane=" + airplane +
                ", departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity +
                ", distance=" + distance +
                ", departing=" + departing +
                ", passengers=" + passengers +
                Toolbox.printTicketsFlight(flightTickets,this)+
                '}';
    }
}
