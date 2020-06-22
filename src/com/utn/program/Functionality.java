package com.utn.program;

import com.utn.aerotaxi.Company;
import com.utn.aerotaxi.Flight;
import com.utn.aerotaxi.FlightTicket;
import com.utn.enums.ECities;
import com.utn.enums.EDistance;
import com.utn.person.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Functionality {
    private Company com;
    private static final Scanner scan = new Scanner(System.in);

    public Functionality() {}
    public Functionality(Company company) {
        this.com = company;
    }

    public void startupMenu() {
        int option;
        do {
            System.out.println("\n" + com.getName());
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse");
            System.out.println("0. Salir");
            System.out.print("\nOpción: ");
            option = scan.nextInt();

            switch (option) {
                case 1 -> loginMenu(com);
                case 2 -> {
                    Passenger pa = signUp();
                    com.addUser(pa);
                }
                case 0 -> System.exit(0);
                default -> {
                    System.out.println("Opción inválida");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } while (option != 0);
    }

    public void loginMenu(Company com) {
        Login aux = new Login();
        scan.nextLine();

        System.out.print("\nIngrese su DNI: ");
        aux.setUserDni(scan.nextLine());
        System.out.print("Ingrese su contraseña: ");
        aux.setPassword(scan.nextLine());
        Person p = Functionality.checkUser(com, aux);

        if (p == null) {
            System.out.println("Los datos ingresados son incorrectos.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } else {
            if (p instanceof Admin)
                menuAdmin((Admin) p);
            else if (p instanceof Passenger)
                menuPassenger((Passenger) p);
        }
    }

    public Passenger signUp() {
        Passenger newPerson = new Passenger();
        scan.nextLine();

        try {
            System.out.print("Ingrese su nombre: ");
            newPerson.setName(scan.nextLine());
            System.out.print("Ingrese su apellido: ");
            newPerson.setLastName(scan.nextLine());
            System.out.print("Ingrese su edad: ");
            newPerson.setAge(scan.nextInt());
            System.out.print("Ingrese su DNI: ");
            scan.nextLine();
            newPerson.setDni(scan.nextLine());
            System.out.print("Ingrese una contraseña: ");
            newPerson.setPassword(scan.nextLine());
        } catch (InputMismatchException ex) {
            System.out.println("Dato ingresado inválido.");
        }

        return newPerson;
    }

    public void menuPassenger (Passenger p) {
        //TODO
        System.out.println("1. Contratar un nuevo vuelo");
        System.out.println("2. Cancelar un vuelo");
        System.out.print("\nOpción: ");
        int option = scan.nextInt();

        if (option == 1) {
            buyFlight(p);
        }
    }

    public static void menuAdmin (Admin a) {
        //TODO
        System.out.println("1. Listar vuelos segun fecha");
        System.out.println("2. Listar pasajeros");
        System.out.print("\nOpción: ");
        int option = scan.nextInt();
    }

    public static Person checkUser(Company com, Login user) {
        if (!com.getAdmins().isEmpty())
            for (Admin a : com.getAdmins())
                if (user.getUserDni().compareTo(a.getDni()) == 0)
                    if (user.getPassword().compareTo(a.getPassword()) == 0)
                        return a;

        if (!com.getPassengers().isEmpty())
            for (Passenger p : com.getPassengers())
                if (user.getUserDni().compareTo(p.getDni()) == 0)
                    if (user.getPassword().compareTo(p.getPassword()) == 0)
                        return p;

        return null;
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

    public void buyFlight(Passenger p) {
        FlightTicket newFlightTicket = new FlightTicket(p);
        boolean confirmed;

        ///Genero ticket
        confirmed = createFlightTicket(newFlightTicket);

        if (confirmed) {
            ///Si el usuario confirma hay que agregarlo a un Flight ya existente o crear una nueva instancia de Flight
            // y agregar el ticket ahí
            ///no olvidar asignar ID en flightTicket
            Flight flight = com.searchFlightForAirplaneAndDate(newFlightTicket.getAirplane(), newFlightTicket.getDeparting());
            if (flight != null) {
                newFlightTicket.setFlightID(flight.getId());
            } else {
                //Si el flight no existe lo creamos
                flight = new Flight(newFlightTicket.getAirplane(), newFlightTicket.getDepartureCity(),
                        newFlightTicket.getArrivalCity(), newFlightTicket.getDeparting());
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
            com.showAvaibleFlights(numberOfPassengers, departureCity, departingDate);
            String id = scan.nextLine();
            ///Corroboramos que exista el avion
            if (com.existAirplane(id)) {
                ///Calculamos el costo
                ticketCost = com.calculateTicketCostForAirplanId(id, numberOfPassengers, distance);
                ///Guardamos el avion en el ticket
                newTicket.setAirplane(com.searchAirplaneForId(id));
            } else {
                System.out.println("El avion ingresado no existe");
            }
        } catch (InputMismatchException e) {
            System.out.println("El id ingresado es inválido");
        }
        return ticketCost;
    }
}
