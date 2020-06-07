package com.utn.aerotaxi;

import com.utn.airplanes.Airplane;
import com.utn.enums.ECities;
import com.utn.passenger.Passenger;
import com.utn.tools.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

public class Flight implements Serializable {
    private String id;
    private Airplane airplane;
    private ECities departureCity;
    private ECities arrivalCity;
    private int distance;
    private LocalDate departing;
    private LinkedList<Passenger> passengers=new LinkedList<>();
    private static int countOfPassengers=1;

    public Flight(){}

    public Flight(Airplane airplane, ECities departureCity,
                  ECities arrivalCity, LocalDate departing, int numberOfCompanions) {
        if(airplane !=null) {
            //if is enough space on the flight
            if ((countOfPassengers + (numberOfCompanions + 1)) < airplane.getMaxPassengerCapacity()) {
                this.id = toolbox.setId();
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

    public Airplane getAirplane(){return this.airplane;}

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
                '}';
    }
}
