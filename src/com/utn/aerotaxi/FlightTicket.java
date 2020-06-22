package com.utn.aerotaxi;

import com.utn.enums.ECities;
import com.utn.person.Passenger;

import java.io.Serializable;
import java.time.LocalDate;

public class FlightTicket implements Serializable {
    private String flightID;
    private Passenger mainPassenger;
    private LocalDate departing;
    private ECities departureCity;
    private ECities arrivalCity;
    private int numberOfPassengers;
    private Double totalTicketCost;
    private boolean status;

    public FlightTicket() {
    }

    public FlightTicket(Passenger mainPassenger, LocalDate departing, ECities departureCity,
                        ECities arrivalCity, String flightID, int numberOfPassengers,
                        double flightCost, int flightFare) {
        this.mainPassenger = mainPassenger;
        this.departing = departing;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.numberOfPassengers = numberOfPassengers;
        this.flightID = flightID;
        this.totalTicketCost = setTotalTicketCost(flightCost, flightFare);
        this.status = true;
    }

    public Double setTotalTicketCost(double flightCost, int flightFare) { //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo de avión)
        return flightCost + ((numberOfPassengers + 1) * 3500) + flightFare;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers + 1;
    }

    public Passenger getMainPassenger() {
        return mainPassenger;
    }

    public String flightID() {
        return flightID;
    }

    /**
     * Cancel the ticket if is at least 1 day before the flight
     * @param today Current day
     * @return True = Is canceled
     *          False = Is not canceled
     */
    public boolean cancelTicket(LocalDate today)
    {
        if (today == null) return false;
        else return this.status = today.datesUntil(departing).count() > 1;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "FlightTicket{" +
                "flightID=" + flightID +
                ", departing=" + departing +
                ", departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity +
                ", numberOfPassengers=" + numberOfPassengers +
                ", totalTicketCost=" + totalTicketCost +
                '}';
    }
}
