package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.enums.ECities;

import java.util.Date;

public class FlightTicket {
    private Flight flight;
    private Date departing;
    private ECities departureCity;
    private ECities arrivalCity;
    private int numberOfPassengers;
    private Double totalTicketCost;

    public FlightTicket(){}

    public FlightTicket(Date departing, ECities departureCity,
                        ECities arrivalCity, Flight flight, int numberOfPassengers) {
        this.departing = departing;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.numberOfPassengers = numberOfPassengers;
        this.totalTicketCost = setTotalTicketCost();
        this.flight=flight;
    }

    private Double setTotalTicketCost() {
        //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo de avión)
        Airplane airplane = flight.getAirplane();

        return (flight.getDistance()*airplane.getCostPerKM())+
                ((numberOfPassengers+1)*3500)+
                airplane.getFlightFare();
    }
}
