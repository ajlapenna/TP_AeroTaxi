package com.utn.tools;

import com.utn.aerotaxi.Flight;
import com.utn.aerotaxi.FlightTicket;
import com.utn.person.Passenger;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Toolbox implements Serializable {
    Toolbox() {
    }

    /**
     * Get a random UUID , transform it to String,
     * reduce it to 10 character long and replace the " - " symbol
     * in order to get a clean alphanumeric id.
     *
     * @return {@link String} = clean alphanumeric id
     */
    public static String setId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    /**
     * Get a random value for the cost.
     *
     * @return {@link Double} = value from 150 to 300 with only 2 decimals
     */
    public static double costRandom() {
        return Math.round((Math.random() * (300 - 150)) + 150);
    }

    ///Print all the passengers
    public static String printTicketsFlight(List<Passenger> passengers, Flight flight) {
        if (flight == null) return "E R R O R Flight not exist.";
        if (passengers == null) {
            return "E R R O R Ticket not exist.";
        } else if (passengers.isEmpty()) {
            return "No tickets added to the fight";
        } else {
            StringBuilder stringOfPassengers = new StringBuilder();
            for (Passenger p : passengers) {
                int i = 1;
                stringOfPassengers.append("[").append(i).append("] ");
                stringOfPassengers.append("\tPassenger: ");
                stringOfPassengers.append(p.getName() + p.getLastName() + p.getDni());
                stringOfPassengers.append("\n");

            }
            stringOfPassengers.append("from: ").append(flight.getDepartureCity().getCityName()).append("\n");
            stringOfPassengers.append("to: " + flight.getArrivalCity().getCityName() + "\n");
            return "\nPassengers of the Flight " + ":\n" + stringOfPassengers.toString();
        }
    }

    public static Flight searchFlightPerID(String id, LinkedList<Flight> flights) {
        Flight flight = null;
        for (Flight currentFlight : flights) {
            if (currentFlight.getId().equalsIgnoreCase(id)) {
                flight = currentFlight;
            }
        }
        return flight;
    }

    public static boolean checkAge(int age) {
        return (age > 0) && (age < 100);
    }

    public static boolean checkDni(String dni) {
        return (dni.length() > 6) && (dni.length() < 9);
    }

}
