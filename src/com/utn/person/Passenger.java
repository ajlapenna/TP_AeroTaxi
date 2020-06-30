package com.utn.person;

import com.utn.aerotaxi.Flight;
import com.utn.aerotaxi.FlightTicket;
import com.utn.airplanes.BronzeFleet;
import com.utn.airplanes.GoldFleet;
import com.utn.airplanes.SilverFleet;
import com.utn.tools.Toolbox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Passenger extends Person {
    private double totalSpend;
    private String bestAirplane;
    private ArrayList<FlightTicket> flightTickets = new ArrayList<>();

    public Passenger() {
    }

    public Passenger(String name, String lastName, String dni, int age, String password) {
        super(name, lastName, dni, age, password);
        totalSpend = 0;
        bestAirplane = null;
        flightTickets = new ArrayList<>();
    }

    public boolean unsuscribeTicket(FlightTicket ft) {
        if (LocalDate.now().isBefore(ft.getDeparting().plusDays(-1))) {
            return true;
        } else {
            System.out.println("Debe cancelarse con al menos 24 horas de anticipación");
        }
        return false;
    }

    public String showAllFlightTickets() {
        StringBuilder showTickets = new StringBuilder();
        for (FlightTicket currentTicket : flightTickets) {
            if (currentTicket.getStatus()) {
                showTickets.append(currentTicket.toString());
                showTickets.append("\n");
            }
        }
        return showTickets.toString();
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public String getBestAirplane() {
        return bestAirplane;
    }

    public void setBestAirplane() {
        int b = 0, s = 0, g = 0;
        if (!flightTickets.isEmpty()) {
            for (FlightTicket f : flightTickets) {
                if (f.getAirplane() instanceof BronzeFleet)
                    b++;
                else if (f.getAirplane() instanceof SilverFleet)
                    s++;
                else if (f.getAirplane() instanceof GoldFleet)
                    g++;
            }
            bestAirplane=((b>s)&&(b>g))? "Bronze":((s>g)&&(s>b))? "Silver": "Gold";
        }
    }

    public ArrayList<FlightTicket> getFlightTickets() {
        return flightTickets;
    }

    public void setFlightTickets(ArrayList<FlightTicket> flightTickets) {
        this.flightTickets = flightTickets;
    }

    public void addFlight(FlightTicket f) {
        if (f != null) {
            flightTickets.add(f);
            setBestAirplane();
        }
    }

    public FlightTicket searchTicketForId(String id) {
        FlightTicket toSearch = null;
        for (FlightTicket ft : flightTickets) {
            if (id.compareToIgnoreCase(ft.getFlightID()) == 0)
                toSearch = ft;
        }
        return toSearch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(getDni(), passenger.getDni()) &&
                Objects.equals(getLastName(), passenger.getLastName()) &&
                Objects.equals(getName(), passenger.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni(), getLastName(), getName());
    }

    @Override
    public String toString() {
        setBestAirplane();
        return super.toString() +
                " totalSpend=" + totalSpend +
                ", bestAirplane='" + bestAirplane + '\'' +
                ", flights=" + Toolbox.printFlightPassenger(flightTickets);
    }
}
