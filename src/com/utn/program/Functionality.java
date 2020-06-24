package com.utn.program;

import com.utn.aerotaxi.Company;
import com.utn.aerotaxi.Flight;
import com.utn.aerotaxi.FlightTicket;
import com.utn.enums.ECities;
import com.utn.enums.EDistance;
import com.utn.person.*;
import com.utn.tools.JsonTools;
import com.utn.tools.Toolbox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Functionality {
    private static Company com;
    private static final Scanner scan = new Scanner(System.in);

    public Functionality() {
    }

    public static void clearScreen() {
        for (int i = 0; i < 80 * 300; i++) {
            System.out.println("\b");
        }
    }

    public Functionality(Company company) {
        this.com = company;
    }

    public static void startupMenu() {
        int option = -1;
        do {
            try {
                System.out.println("\n" + com.getName());
                System.out.println("1. Ingresar");
                System.out.println("2. Registrarse");
                System.out.println("0. Salir");
                System.out.print("\nOpción: ");
                option = scan.nextInt();

                switch (option) {
                    case 1:
                        loginMenu(com);
                        break;
                    case 2:
                        Passenger pa = (Passenger) signUp();
                        com.addUser(pa);
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("E R R O R! not a correct option. ");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                }
            } catch (InputMismatchException ex) {
                clearScreen();
                System.out.println("E R R O R! not a correct option. ");
                scan.nextLine();
            }
        } while (option != 0 || option == -1);
    }

    public static void loginMenu(Company com) {
        Login aux = new Login();
        Person p = null;
        scan.nextLine();
        int flag = 0;
        do {
            System.out.print("\nIngrese su DNI: ");
            aux.setUserDni(scan.nextLine());
            System.out.print("Ingrese su contraseña: ");
            aux.setPassword(scan.nextLine());
            p = Functionality.checkUser(com, aux);
            if (p == null) {
                System.out.println("Los datos ingresados son incorrectos.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else flag = 1;
        } while (flag == 0);
        if (p instanceof Admin)
            menuAdmin((Admin) p);
        else if (p instanceof Passenger)
            menuPassenger((Passenger) p);

    }

    public static Passenger signUp() {
        Passenger newPerson = new Passenger();
        scan.nextLine();
        int flag = 1;
        do {
            try {
                System.out.print("Ingrese su nombre: ");
                newPerson.setName(scan.nextLine());
                System.out.print("Ingrese su apellido: ");
                newPerson.setLastName(scan.nextLine());
                do {
                    System.out.print("Ingrese su edad: ");
                    newPerson.setAge(scan.nextInt());
                } while (!Toolbox.checkAge(newPerson.getAge()));
                scan.nextLine();
                do {
                    System.out.print("Ingrese su DNI: ");
                    newPerson.setDni(scan.nextLine());
                } while (!Toolbox.checkDni(newPerson.getDni()));
                scan.nextLine();
                System.out.print("Ingrese una contraseña: ");
                newPerson.setPassword(scan.nextLine());
            } catch (InputMismatchException ex) {
                System.out.println("Dato ingresado inválido.");
                flag = 0;
            } finally {
                scan.nextLine();
            }
        } while (flag == 0);
        return newPerson;
    }


    public static Admin signUpAdmin() {
        Admin newPerson = new Admin();
        scan.nextLine();
        int flag = 1;
        do {
            try {
                System.out.print("Ingrese su nombre: ");
                newPerson.setName(scan.nextLine());
                System.out.print("Ingrese su apellido: ");
                newPerson.setLastName(scan.nextLine());
                do {
                    System.out.print("Ingrese su edad: ");
                    newPerson.setAge(scan.nextInt());
                } while (!Toolbox.checkAge(newPerson.getAge()));
                do {
                    System.out.print("Ingrese su DNI: ");
                    newPerson.setDni(scan.nextLine());
                } while (!Toolbox.checkDni(newPerson.getDni()));
                System.out.print("Ingrese una contraseña: ");
                newPerson.setPassword(scan.nextLine());
            } catch (InputMismatchException ex) {
                System.out.println("Dato ingresado inválido.");
                flag = 0;
            } finally {
                scan.nextLine();
            }
        } while (flag == 0);
        return newPerson;
    }

    private static void menuPassenger(Passenger p) {
        //TODO
        System.out.println("1. Contratar un nuevo vuelo");
        System.out.println("2. Cancelar un vuelo");
        System.out.print("\nOpción: ");
        int option = scan.nextInt();

        if (option == 1) {
            buyFlight(p);
        }
    }

    public static void menuAdmin(Admin a) {
        //TODO
        int flag = 0, option = 0;
        try {
            do {
                System.out.println("1. Listar vuelos");
                System.out.println("2. Listar vuelos por fecha");
                System.out.println("3. Listar pasajeros");
                System.out.println("4. Crear nuevo admin");
                System.out.println("0. Salir");
                System.out.print("\nOpción: ");
                option = scan.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Listar vuelos:");
                        System.out.println(com.showAllFlights());
                        break;
                    case 2:
                        System.out.println("Listar vuelos por fecha:");
                        LocalDate currentDate = insertDepartingDate();
                        System.out.println("Listar vuelos por fecha:");
                        System.out.println(com.showAllFlightsByDay(currentDate));
                        break;
                    case 3:
                        System.out.println("Listar Pasajeros:");
                        System.out.println(com.showAllPassengers());
                        break;
                    case 4:
                        System.out.println("Crear nuevo Admin:");
                        Admin newAdm = signUpAdmin();
                        com.addUser(newAdm);
                        break;
                    case 0:
                        flag = 1;
                        startupMenu();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + option);
                }
            } while (flag == 0);
        } catch (InputMismatchException ex) {
            System.out.println("Debes insertar un número");
            option = scan.nextInt();
        }
    }

    public static Person checkUser(Company com, Login user) {
        if (com != null) {
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
        }
        return null;
    }

    private static boolean createFlightTicket(FlightTicket newFlightTicket) {

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
            double ticketCost = 0;
            ticketCost = insertTicketCost(newFlightTicket, newFlightTicket.getNumberOfPassengers(),
                    newFlightTicket.getDepartureCity(), newFlightTicket.getDeparting(), distance);
            if(ticketCost != -1)
                newFlightTicket.setTotalTicketCost(ticketCost);
        }
        ///Al tener precio calculado, preguntamos si desea confirmar su vuelo
        if (newFlightTicket.getTotalTicketCost() != -1) {
            if (confirmedflight(newFlightTicket) == 1)
                 confirmed = true;
        }
        return confirmed;
    }

    private static int confirmedflight(FlightTicket newFlightTicket) {
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

    private static void buyFlight(Passenger p) {

        FlightTicket newFlightTicket = new FlightTicket();
        boolean confirmed;

        ///Genero ticket
        confirmed = createFlightTicket(newFlightTicket);

        if (confirmed) {
            ///Si el usuario confirma hay que agregarlo a un Flight ya existente o crear una nueva instancia de Flight
            // y agregar el ticket ahí
            Flight flight = com.searchFlightForAirplaneAndDate(newFlightTicket.getAirplane(), newFlightTicket.getDeparting());

            if (flight != null) {
                newFlightTicket.setFlightID(flight.getId());
            } else {
                //Si el flight no existe lo creamos
                flight = new Flight(newFlightTicket.getAirplane(), newFlightTicket.getDepartureCity(),
                        newFlightTicket.getArrivalCity(), newFlightTicket.getDeparting());
                flight.addPassenger(p);
                System.out.println(flight.toString());
                //Lo agregamos a la lista de vuelos en company
                com.addFlight(flight);
             //   com.getFlights().add(flight);
                //Registramos proxima fecha de salida para el avion con su proximo destino
                newFlightTicket.getAirplane().setNextCity(newFlightTicket.getArrivalCity());
                newFlightTicket.getAirplane().setNextDepartingDate(newFlightTicket.getDeparting());
            }
            //Agregamos ticket a Flight
            flight.addFlightTicket(newFlightTicket);
            p.addFlight(newFlightTicket);

            //persistimos datos
            JsonTools.writeJson(com.getPassengers(),JsonTools.fpassengers);
            JsonTools.writeJson(com.getFlights(), JsonTools.fflights);
            System.out.println("Vuelo registrado correctamente");
        } else {
            System.out.println("El vuelo no se ha registrado");
        }

    }

    private static LocalDate insertDepartingDate() {
        LocalDate date = null;
        try {
            scan.nextLine();
            System.out.println("Introduzca la fecha con formato dd/mm/yyyy");
            String fechaComoTexto = scan.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(fechaComoTexto, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("La fecha ingresada es inválida, por favor inténtelo nuevamente");
        }

        return date;
    }

    private static ECities insertDepartureCity() {
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

    private static ECities insertArrivalCity() {
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

    private static int insertNumberOfPassengers() {
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
    private static int searchDistance(ECities origen, ECities destino) {
        int rta = 0;
        for (EDistance distancia : EDistance.values()) {
            if (distancia.getOrigen().getCityName().compareToIgnoreCase(origen.getCityName()) == 0 &&
                    distancia.getDestino().getCityName().compareToIgnoreCase(destino.getCityName()) == 0) {
                rta = distancia.getDistance();
            }
        }
        return rta;
    }

    private static double insertTicketCost(FlightTicket newTicket, int numberOfPassengers, ECities departureCity, LocalDate departingDate, int distance) {

        double ticketCost = 0;
        try {
            if (com.showAvailableAirplanes(numberOfPassengers, departureCity, departingDate)) {
                System.out.println("Por favor, seleccione el ID del avion en el que desea viajar");
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
            } else {
                System.out.println("No tenemos aviones disponibles con esa capacidad de pasajeros");
                ticketCost = -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("El id ingresado es inválido");
        }
        return ticketCost;
    }
}
