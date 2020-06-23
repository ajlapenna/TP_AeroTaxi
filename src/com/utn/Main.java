package com.utn;

import com.utn.aerotaxi.*;
import com.utn.airplanes.*;
import com.utn.enums.*;
import com.utn.person.*;
import com.utn.program.Functionality;
import com.utn.tools.JsonTools;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Passenger> passengers = new LinkedList<>();
        passengers.add(new Passenger("Agus", "Iri", "40138021", 25, "1234"));
        passengers.add(new Passenger("Agus", "Lap", "33984783", 25, "1234"));
        passengers.add(new Passenger("Gonza", "rom", "99789654", 25, "1234"));
        JsonTools.writeJson(passengers, JsonTools.fpassengers);

        List<Admin> admins = new LinkedList<>();
        admins.add(new Admin("Agus", "Lapee", "38283270", 26, "1234"));
        JsonTools.writeJson(admins, JsonTools.fadmins);

        /*List<Airplane> airplanes = new LinkedList<>();
        airplanes.add(new GoldFleet(5000, 10, 900, EPropulsionType.REACTION));
        airplanes.add(new SilverFleet(4000, 20, 999, EPropulsionType.PISTON));
        airplanes.add(new BronzeFleet(6000, 30, 750, EPropulsionType.PROPELLER));
        JsonTools.writeJson(airplanes, JsonTools.fairplanes);*/

        /*List<Flight> flights = new LinkedList<>();
        flights.add(new Flight(airplanes.get(2), ECities.MONTVIDE, ECities.BSAS, LocalDate.now()));
        FlightTicket ticket1 = new FlightTicket(passengers.get(0), LocalDate.now(),
                ECities.MONTVIDE, ECities.BSAS, flights.get(0).getId(), 5);
        flights.get(0).addFlightTicket(ticket1);
        JsonTools.writeJson(flights, JsonTools.fflights);*/

        Company com1 = new Company("AeroTaxi");
        //Functionality program = new Functionality(com1);


        Functionality start = new Functionality(com1);
        start.startupMenu();
    }


}
