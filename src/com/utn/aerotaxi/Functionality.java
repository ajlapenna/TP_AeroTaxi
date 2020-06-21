package com.utn.aerotaxi;

import com.utn.passenger.Passenger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Functionality {

    private Company company;
    Scanner scan = new Scanner(System.in);

    public Functionality(Company company) {
        this.company = company;
    }

    private boolean validationUser() {
        try {
            System.out.print("Ingrese su DNI: ");
            String dni = String.valueOf(scan.nextInt());
            if (company.existPassenger(dni))
                return true;
            else {
                System.out.println("Usted no se encuentra en el sistema. Por favor: ");
                company.addPassenger(createUser(dni));
                return true;
            }
        } catch (InputMismatchException e) {
            System.out.println("El DNI ingresado es inválido.");
            scan.nextLine();
        }
        return false;
    }

    public Passenger createUser(String dni) {
        Passenger newPassenger = new Passenger();
        newPassenger.setDni(dni);
        try {
            System.out.print("Ingrese su nombre: ");
            String name = scan.nextLine();
            newPassenger.setName(name);

            System.out.print("Ingrese su apelledio: ");
            String lastName = scan.nextLine();
            newPassenger.setLastName(lastName);

            System.out.print("Ingrese su edad: ");
            int age = scan.nextInt();
            newPassenger.setAge(age);
        } catch (InputMismatchException e) {
            System.out.println("Datos ingresados inválidos");
        }
        finally {
            //Limpiar scanner
            scan.nextLine();
        }
        return newPassenger;
    }

    public void startProgram(){
        boolean log = false;
        while(!log){
            log = validationUser();
        }
        menu();
    }

    public void menu() {
        int menu = 0;
        try {
            System.out.println("1. Contratar un nuevo vuelo");
            menu = scan.nextInt();

            switch (menu) {
                case 1:
                    //hacer formulario
                    System.out.println("Aqui habia ponido yo mi case 1");
                    break;

                case 2:
                    System.out.println("Aqui habia ponido yo mi case 2");
                    break;

            }
        } catch (InputMismatchException e) {
            System.out.println("Opción ingresada incorrectamente");
        }
    }


}



