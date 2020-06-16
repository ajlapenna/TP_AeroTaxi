package com.utn.tools;

import com.utn.aerotaxi.Flight;
import com.utn.aerotaxi.FlightTicket;
import com.utn.passenger.Passenger;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class toolbox implements Serializable {
    toolbox() {
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
    public static Double costRandom() {
        return Math.round(Math.random() * (150 - 300 + 1) + 300 * 100d) / 100d;
    }

    ///Print all the employees from the current site
    public static String printTicketsFlight(List<FlightTicket> flightTickets, Flight flight) {
        if (flightTickets == null) {
            return "E R R O R Ticket not exist.";
        } else if (flightTickets.isEmpty()) {
            return "No tickets added to the fight";
        } else {
            StringBuilder stringOfPassengers = new StringBuilder();
            stringOfPassengers.append("from: " + flight.getDepartureCity().getCityName() + "\n");
            stringOfPassengers.append("to: " + flight.getArrivalCity().getCityName() + "\n");
            for (FlightTicket ticket : flightTickets) {
                int i = 1;
                stringOfPassengers.append("[").append(i).append("] ");
                stringOfPassengers.append("\tName: ");
                stringOfPassengers.append(ticket.getMainPassenger().getName());
                stringOfPassengers.append("  Dni: ");
                stringOfPassengers.append(ticket.getMainPassenger().getDni());
                stringOfPassengers.append("\n");
            }
            return "\nPassengers of the Flight "+ ":\n" + stringOfPassengers.toString();
        }
    }

    public static Flight searchFlightPerID(String id, LinkedList<Flight> flights)
    {
        Flight flight = null;
        for (Flight currentFlight : flights){
            if(currentFlight.getId().equalsIgnoreCase(id)){
                flight=currentFlight;
            }
        }
        return flight;
    }
}
