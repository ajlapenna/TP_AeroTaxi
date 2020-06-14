package com.utn;
import com.utn.aerotaxi.*;
import com.utn.passenger.*;
import com.utn.tools.JsonTools;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        //Sandbox:::::::::
        List<Passenger> passengers = new LinkedList<>();
        passengers.add(new Passenger("Agus","Iri","40138021",25));
        passengers.add(new Passenger("Agus","Lap","33984783",25));
        passengers.add(new Passenger("Gonza","rom","99789654",25));
        JsonTools.writePassengerJson(passengers,JsonTools.fpassengers);
        passengers.clear();
        passengers=JsonTools.readPassengerJson(JsonTools.fpassengers,Passenger.class);
        for(Passenger passenger : passengers){
            System.out.println(passenger.toString());
        }

        Company com1 = new Company("AeroTaxi");

    }
}
