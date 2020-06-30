package com.utn;

import com.utn.aerotaxi.*;
import com.utn.airplanes.*;
import com.utn.enums.*;
import com.utn.person.*;
import com.utn.program.Functionality;
import com.utn.tools.JsonTools;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company com1 = new Company("AeroTaxi");
        Functionality.setCom(com1);
        Functionality.startupMenu();

    }
}
