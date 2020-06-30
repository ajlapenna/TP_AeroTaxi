package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.enums.ECities;
import com.utn.person.Passenger;
import com.utn.tools.Toolbox;

import java.io.Serializable;
import java.time.LocalDate;

public class FlightTicket implements Serializable {
    private String flightID;
    private String mainPassenger;
    private LocalDate departing;
    private ECities departureCity;
    private ECities arrivalCity;
    private int numberOfPassengers;
    private double totalTicketCost;
    private boolean status;
    private Airplane airplane;

    public FlightTicket() {
        this.flightID = Toolbox.setId();
        status = true;
    }

    public FlightTicket(Passenger passenger){
        this.mainPassenger = passenger.getName() + passenger.getDni();
        this.totalTicketCost = 0;
        this.numberOfPassengers = 0;
        this.arrivalCity = null;
        this.departureCity = null;
        this.flightID = Toolbox.setId();
        status = true;
    }

    public FlightTicket(Passenger mainPassenger, LocalDate departing, ECities departureCity,
                        ECities arrivalCity, int numberOfPassengers) {
        this.mainPassenger =  mainPassenger.getName() + mainPassenger.getDni();
        this.departing = departing;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.numberOfPassengers = numberOfPassengers;
        this.flightID = Toolbox.setId();
        this.status = true;
    }

    public void setTotalTicketCost(Double totalTicketCost) {
        this.totalTicketCost = totalTicketCost;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airplane getAirplane() {
        return airplane;
    }

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
  
    public Double getTotalTicketCost() {
        return totalTicketCost;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public String getMainPassenger() {
        return mainPassenger;
    }

    public String getFlightID() {
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public String getAirplaneFleet(){
        return airplane.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "FlightTicket{" +
                "flightID='" + flightID + '\'' +
                ", mainPassenger='" + mainPassenger + '\'' +
                ", departing=" + departing +
                ", departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity +
                ", numberOfPassengers=" + numberOfPassengers +
                ", totalTicketCost=" + totalTicketCost +
                ", status=" + status +
                ", airplane=" + airplane +
                '}';
    }
}
