package com.utn.aerotaxi;

import com.utn.enums.ECities;
import com.utn.enums.EDistance;
import com.utn.passenger.Passenger;
import com.utn.passenger.Person;
import com.utn.tools.JsonTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Functionality {

    private Company company;
    private Passenger userLogIn; //para no perder usuario logueado
    private Scanner scan = new Scanner(System.in);

    public Functionality(Company company) {
        this.company = company;
        this.userLogIn = new Passenger();
    }

    private boolean validationUser() {
        boolean rta = false;
        try {
            System.out.println("Ingrese su DNI");
            String dni = scan.nextLine();
            if (company.existClient(dni) == true)
                rta = true;
            else {
                System.out.println("Usted no se encuentra en el sistema por favor: ");
                company.addClient(createUser(dni));
                rta = true;
            }
            ///Guardo el usuario logeando en atributo
            this.userLogIn = searchClientForDni(dni);
        } catch (InputMismatchException e) {
            System.out.println("El DNI ingresado es inválido");
        }
        return rta;
    }

    public Passenger createUser(String dni) {
        Passenger newPassenger = new Passenger();
        newPassenger.setDni(dni);
        try {
            System.out.println("Ingrese su nombre");
            String name = scan.nextLine();
            newPassenger.setName(name);
            System.out.println("Ingrese su apelledio");
            String lastName = scan.nextLine();
            newPassenger.setLastName(lastName);
            System.out.println("Ingrese su edad");
            int age = scan.nextInt();
            newPassenger.setAge(age);
        } catch (InputMismatchException e) {
            System.out.println("Datos ingresados inválidos");
        } finally {
            //Limpiar scanner
            scan.nextLine();
        }
        company.addPassenger(newPassenger);
        return newPassenger;
    }

    public void startProgram() {

        boolean log = false;

        while (log == false) {
            log = validationUser();
        }
        usersMenu();

    }

    public Passenger searchClientForDni(String dni) {
        Passenger searchFor = new Passenger();
        for (Passenger p : this.company.getClients()) {
            if (p.getDni().compareTo(dni) == 0)
                searchFor = p;
        }
        return searchFor;
    }

    public LocalDate insertDepartingDate() {
        LocalDate date = null;
        try {
            System.out.println("Introduzca la fecha con formato dd/mm/yyyy");
            String fechaComoTexto = scan.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            date = LocalDate.parse(fechaComoTexto, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("La fecha ingresada es inválida, por favor inténtelo nuevamente");
        }

        return date;
    }

    public ECities insertDepartureCity() {
        ECities rta = null;

        System.out.println("Ingrese su ciudad de origen");
        String city = scan.nextLine();
        for (ECities c : ECities.values()) {
            if (c.getCityName().compareToIgnoreCase(city) == 0)
                rta = c;
        }
        if (rta == null)
            System.out.println("La ciudad de origen ingresada es incorrecta, por favor vuelva a intentarlo");
        return rta;
    }

    public ECities insertArrivalCity() {
        ECities rta = null;

        System.out.println("Ingrese su ciudad de destino");
        String city = scan.nextLine();
        for (ECities c : ECities.values()) {
            if (c.getCityName().compareToIgnoreCase(city) == 0)
                rta = c;
        }
        if (rta == null)
            System.out.println("La ciudad de destino ingresada es incorrecta, por favor vuelva a intentarlo");

        return rta;
    }

    public int insertNumberOfPassengers() {
        int passengers = 0;
        try {
            System.out.println("Ingrese la cantidad de pasajeros");
            passengers = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("La cantidad ingresada es inválida, por favor vuelva a intentarlo");
        } finally {
            //limpio scan
            scan.nextLine();
        }
        return passengers;
    }

    ///Busco la distacia correspondiente a un origen y destino enviado por parametro
    public int searchDistance(ECities origen, ECities destino) {
        int rta = 0;
        for (EDistance distancia : EDistance.values()) {
            if (distancia.getOrigen().getCityName().compareToIgnoreCase(origen.getCityName()) == 0 &&
                    distancia.getDestino().getCityName().compareToIgnoreCase(destino.getCityName()) == 0) {
                rta = distancia.getDistance();
            }
        }
        return rta;
    }

    public double insertTicketCost(FlightTicket newTicket, int numberOfPassengers, ECities departureCity, LocalDate departingDate, int distance) {

        double ticketCost = 0;
        try {
            System.out.println("Por favor, seleccione el ID del avion en el que desea viajar");
            company.showAvaibleFlights(numberOfPassengers, departureCity, departingDate);
            String id = scan.nextLine();
            ///Corroboramos que exista el avion
            if (company.existAirplane(id)) {
                ///Calculamos el costo
                ticketCost = company.calculateTicketCostForAirplanId(id, numberOfPassengers, distance);
                ///Guardamos el avion en el ticket
                newTicket.setAirplane(company.searchAirplanForId(id));
            } else {
                System.out.println("El avion ingresado no existe");
            }
        } catch (InputMismatchException e) {
            System.out.println("El id ingresado es inválido");
        }
        return ticketCost;
    }


    public void buyFlight() {

        FlightTicket newFlightTicket = new FlightTicket(userLogIn);
        boolean confirmed;

        ///Genero ticket
        confirmed = createFlightTicket(newFlightTicket);

        if (confirmed) {
            ///Si el usuario confirma hay que agregarlo a un Flight ya existente o crear una nueva instancia de Flight
            // y agregar el ticket ahí
            ///no olvidar asignar ID en flightTicket
            Flight flight = company.searchFlightForAirplaneAndDate(newFlightTicket.getAirplane(), newFlightTicket.getDeparting());
            if (flight != null) {
                newFlightTicket.setFlightID(flight.getId());
            } else {
                //Si el flight no existe lo creamos
                flight = new Flight(newFlightTicket.getAirplane(), newFlightTicket.getDepartureCity(), newFlightTicket.getArrivalCity(),
                        newFlightTicket.getDeparting());
                flight.addPassenger(newFlightTicket.getMainPassenger());
                //Registramos proxima fecha de salida para el avion con su proximo destino
                newFlightTicket.getAirplane().setNextCity(newFlightTicket.getArrivalCity());
                newFlightTicket.getAirplane().setNextDepartingDate(newFlightTicket.getDeparting());
            }
            //Agregamos ticket a Flight
            flight.addFlightTicket(newFlightTicket);
            System.out.println("Vuelo registrado correctamente");
        } else {
            System.out.println("El vuelo no se ha registrado");
        }

    }

    public int confirmedflight(FlightTicket newFlightTicket) {
        int rta = 0;

        try {
            System.out.println("El costo total para su vuelo es de $" + newFlightTicket.getTotalTicketCost() +
                    " pulse 1 si desea confirmar, o 0 para cancelar");
            rta = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("La opcion ingresada es inválida");
        } finally {
            //limpio scanner
            scan.nextLine();
        }
        return rta;
    }


    public boolean createFlightTicket(FlightTicket newFlightTicket) {

        boolean confirmed = false;
        //pido datos por teclado llamando a los metodos correspondientes
        while (newFlightTicket.getDeparting() == null) {
            newFlightTicket.setDeparting(insertDepartingDate());
        }
        while (newFlightTicket.getDepartureCity() == null) {
            newFlightTicket.setDepartureCity(insertDepartureCity());
        }
        while (newFlightTicket.getArrivalCity() == null) {
            ECities city = insertArrivalCity();
            //Verifico que no sea igual la de origen que destino
            if (newFlightTicket.getDepartureCity() != city) {
                newFlightTicket.setArrivalCity(city);
            } else {
                System.out.println("La ciudad ingresada no puede ser la misma que la de origen");
            }
        }

        while (newFlightTicket.getNumberOfPassengers() == 0) {
            newFlightTicket.setNumberOfPassengers(insertNumberOfPassengers());
        }
        ///Como ya sabemos ciudad de origen y destino, buscamos la distancia correspondiente en km
        int distance = searchDistance(newFlightTicket.getDepartureCity(), newFlightTicket.getArrivalCity());

        while (newFlightTicket.getTotalTicketCost() == 0) {
            newFlightTicket.setTotalTicketCost(insertTicketCost(newFlightTicket,newFlightTicket.getNumberOfPassengers(),
                    newFlightTicket.getDepartureCity(), newFlightTicket.getDeparting(), distance));
        }
        ///Al tener precio calculado, preguntamos si desea confirmar su vuelo

        if(confirmedflight(newFlightTicket) == 1)
            confirmed = true;

        return confirmed;
    }


    public void usersMenu() {

        int menu = 0;

        try {
            System.out.println("1.Contratar un nuevo vuelo");
            menu = scan.nextInt();


            switch (menu) {
                case 1:
                    buyFlight();
                    break;


            }


        } catch (InputMismatchException e) {
            System.out.println("Opcion ingresada incorrectamente");
        }
    }


}


