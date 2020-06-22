package com.utn.person;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private String password;    //login?
    private String name;
    private String lastName;
    private String dni;         // funciona como userName para el login
    private int age;
    private boolean deleted;

    public Person() {}
    public Person(String name, String lastName, String dni, int age, String password) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.age = age;
        this.password = password;
        this.deleted = false;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
