package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.enums.ECities;
import com.utn.passenger.Passenger;

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

    public FlightTicket(){}

    public FlightTicket(Passenger mainPassenger, LocalDate departing, ECities departureCity,
                        ECities arrivalCity, String flightID, int numberOfPassengers) {
        this.mainPassenger=mainPassenger;
        this.departing = departing;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.numberOfPassengers = numberOfPassengers;
        this.flightID=flightID;
        ///this.totalTicketCost = setTotalTicketCost();
        this.status=true;
    }

    /*private Double setTotalTicketCost()
    {//hacer metodo que busque un vuelo por le ID y devuelva el vuelo
        //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo de avión)
        Airplane airplane = flight.getAirplane();

        return (flight.getDistance()*airplane.getCostPerKM())+
                ((numberOfPassengers+1)*3500)+
                airplane.getFlightFare();
    }*/

    public int getNumberOfPassengers() {
        return numberOfPassengers+1;
    }

    public Passenger getMainPassenger(){ return mainPassenger;}

    public String flightID()
    {
        return flightID;
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
