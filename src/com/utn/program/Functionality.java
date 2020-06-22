package com.utn.program;

import com.utn.aerotaxi.Company;
import com.utn.person.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Functionality {
    private Company com;
    private static final Scanner scan = new Scanner(System.in);
    private static final Pattern pdni = Pattern.compile("\\\\d{8}\"");

    public Functionality() {
    }

    public Functionality(Company company) {
        this.com = company;
    }

    public void startupMenu() {
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
                    case 1 -> loginMenu(com);
                    case 2 -> {
                        Passenger pa = signUp();
                        com.addUser(pa);
                    }
                    case 0 -> System.exit(0);
                    default -> {
                        System.out.println("E R R O R! not a correct option. ");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("E R R O R! not a correct option. ");
                scan.nextLine();
            }
        } while (option != 0 || option == -1);
    }

    public static void loginMenu(Company com)
    {
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
            }
            else flag = 1;
        } while (flag == 0);
        if (p instanceof Admin)
                menuAdmin();
            else if (p instanceof Passenger)
                menuPassenger();
    }

    public Passenger signUp()
    {
        Passenger newPerson = new Passenger();
        scan.nextLine();
        int flag = 1;
        do {
            try {
                System.out.print("Ingrese su nombre: ");
                newPerson.setName(scan.nextLine());
                System.out.print("Ingrese su apellido: ");
                newPerson.setLastName(scan.nextLine());
                System.out.print("Ingrese su edad: ");
                newPerson.setAge(scan.nextInt());
                System.out.print("Ingrese su DNI: ");
                newPerson.setDni(scan.nextLine());
                System.out.print("Ingrese una contraseña: ");
                newPerson.setPassword(scan.nextLine());
            } catch (InputMismatchException ex) {
                System.out.println("Dato ingresado inválido.");
                flag=0;
            }
            finally {
                scan.nextLine();
            }
        }while (flag == 0);
        return newPerson;
    }

    public static void menuPassenger() {
        //TODO
        System.out.println("1. Contratar un nuevo vuelo");
        System.out.println("2. Cancelar un vuelo");
        System.out.print("\nOpción: ");
        scan.nextInt();
    }

    public static void menuAdmin() {
        //TODO
        System.out.println("1. Listar vuelos segun fecha");
        System.out.println("2. Listar pasajeros");
        System.out.print("\nOpción: ");
        scan.nextInt();
    }

    public static Person checkUser(Company com, Login user)
    {
        if(com != null) {
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
}
