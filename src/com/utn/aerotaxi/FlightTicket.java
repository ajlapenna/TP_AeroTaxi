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
    private double totalTicketCost;
    private boolean status;

    public FlightTicket(Passenger passenger){
        this.mainPassenger = passenger;
        this.totalTicketCost = 0;
        this.numberOfPassengers = 0;
        this.arrivalCity = null;
        this.departureCity = null;
    }

    public FlightTicket(Passenger mainPassenger, LocalDate departing, ECities departureCity,
                        ECities arrivalCity, String flightID, int numberOfPassengers) {
        this.mainPassenger=mainPassenger;
        this.departing = departing;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.numberOfPassengers = numberOfPassengers;
        this.flightID=flightID;
        this.totalTicketCost = 0;
        this.status=true;
    }

    //public FlightTicket(Passenger passenger){
     //   this.mainPassenger = passenger;
   // }

    public void setDeparting(LocalDate departing) {
        this.departing = departing;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public void setArrivalCity(ECities arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setDepartureCity(ECities departureCity) {
        this.departureCity = departureCity;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setTotalTicketCost(Double totalTicketCost) {
        this.totalTicketCost = totalTicketCost;
    }

    /*private Double setTotalTicketCost()
    {//hacer metodo que busque un vuelo por le ID y devuelva el vuelo
        //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo de avión)
        Airplane airplane = flight.getAirplane();

        return (flight.getDistance()*airplane.getCostPerKM())+
                ((numberOfPassengers+1)*3500)+
                airplane.getFlightFare();
    }*/

    public Double getTotalTicketCost() {
        return totalTicketCost;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public Passenger getMainPassenger(){ return mainPassenger;}

    public String flightID()
    {
        return flightID;
    }

    public LocalDate getDeparting() {
        return departing;
    }

    public ECities getArrivalCity() {
        return arrivalCity;
    }

    public ECities getDepartureCity() {
        return departureCity;
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
