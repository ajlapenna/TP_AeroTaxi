package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.enums.ECities;
import com.utn.passenger.Passenger;
import com.utn.tools.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.LinkedList;

public class Flight {

    private int id;
    private Airplane airplane;
    private ECities departureCity;
    private ECities arrivalCity;
    private int distance;
    private LocalDate departing;
    private LinkedList<Passenger> passengers=new LinkedList<>();
    private static int countOfPassengers=0;

    public Flight(){}

    public Flight(int id, Airplane airplane, ECities departureCity,
                  ECities arrivalCity, LocalDate departing, int numberOfCompanions) {
        if(airplane !=null) {
            //if is enough space on the flight
            if ((countOfPassengers + (numberOfCompanions + 1)) < airplane.getMaxPassengerCapacity()) {
                this.id = idHandler.setId();
                this.airplane = airplane;
                this.departureCity = departureCity;
                this.arrivalCity = arrivalCity;
                this.departing = departing;
                this.distance = setDistance();
            }
        }
    }

    private int setDistance() {
        return ((departureCity==ECities.BSAS && arrivalCity==ECities.CBA)||
                (departureCity==ECities.CBA &&arrivalCity==ECities.BSAS))? 695:
                ((departureCity==ECities.BSAS && arrivalCity==ECities.SANTCHILE)||
                (departureCity==ECities.SANTCHILE &&arrivalCity==ECities.BSAS))? 1400:
                        ((departureCity==ECities.BSAS && arrivalCity==ECities.MONTVIDE)||
                        (departureCity==ECities.MONTVIDE &&arrivalCity==ECities.BSAS))? 950:
                                ((departureCity==ECities.CBA && arrivalCity==ECities.MONTVIDE)||
                                        (departureCity==ECities.MONTVIDE &&arrivalCity==ECities.CBA))? 1190:
                                        ((departureCity==ECities.CBA && arrivalCity==ECities.SANTCHILE)||
                                                (departureCity==ECities.SANTCHILE &&arrivalCity==ECities.CBA))? 1050:
                                                ((departureCity==ECities.MONTVIDE && arrivalCity==ECities.SANTCHILE)||
                                                        (departureCity==ECities.SANTCHILE &&arrivalCity==ECities.MONTVIDE))? 2100:0;
    }

    public int getDistance(){return this.distance;}

    public Airplane getAirplane(){return airplane;}

    public LinkedList<Passenger> getPassengers() {
        return passengers;
    }

    public boolean setPassengers(LinkedList<Passenger> passengers) {
        if (countOfPassengers<= airplane.getMaxPassengerCapacity()) {
            this.passengers = passengers;
            return true;
        }
        else return false;
    }

    public LocalDate getDeparting() {
        return this.departing;
    }
}
