package com.utn.passenger;

import java.io.Serializable;

public class Person implements Serializable {
    private String username; //login?
    private String password;  //login?
    private String name;
    private String lastName;
    private String dni;
    private int age;
    private boolean deleted;

    //Constructor//
    public Person() {
        this.deleted=false;
    }

    public Person(String name, String lastName, String dni, int age) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.age = age;
        this.deleted=false;
    }

    //login?
    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return name + ", last name='" + lastName + '\'' +", dni='" + dni + '\'' +", age=" + age ;
    }

}
