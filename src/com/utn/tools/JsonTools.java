package com.utn.tools;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.utn.aerotaxi.Flight;
import com.utn.aerotaxi.FlightTicket;
import com.utn.person.Admin;
import com.utn.program.Functionality;
import com.utn.airplanes.Airplane;
import com.utn.person.Passenger;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * JsonTools use <b>Jackson library</b>
 * to serialize Java objects into JSON
 * and deserialize JSON into Java objects
 */
public class JsonTools {
    public static final String fpassengers = "resources/passengers.json";
    public static final String fadmins = "resources/admins.json";
    public static final String fairplanes = "resources/airplanes.json";
    public static final String fflights = "resources/flights.json";

    //Constant of a ObjectMapper
    private static ObjectMapper JSON_MAPPER = null;

    private JsonTools() {
    }

    /**
     * Write a JSON file of a given list of Objects
     *
     * @param list {@link List} The source list
     * @param path {@link String}Path of the file
     */

    public static <t> void writeJson(List<t> list, String path) {
        if (list == null) System.out.println("E R R O R list cant be null");
        else {
            BufferedWriter buffer = null;
            File file = new File(path);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    buffer = new BufferedWriter(new FileWriter(file));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            if (buffer != null) {
                try {
                    getMapper().writerWithDefaultPrettyPrinter().writeValue(buffer, list);
                    buffer.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Generic method to parse a JSON into a Java Object LinkedList of any class
     *
     * @param path   {@link String}of the file
     * @param tClass {@link Class} of the objects in the list
     * @return {@link LinkedList} of the objects in the JSON
     */
    public static <T> List<T> readJson(String path, Class<T> tClass) {
        BufferedReader buffer = null;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                buffer = new BufferedReader(new FileReader(file));
            } catch (IOException ex) {
                System.out.println("E R R O R : " + ex.getMessage());
            }
        }

        List<T> list = null;
        if (buffer != null) {
            try {
                CollectionType listType = getMapper().getTypeFactory().
                        constructCollectionType(LinkedList.class, tClass);
                list = getMapper().readValue(buffer, listType);
            } catch (IOException ex) {
                System.out.println("E R R O R : " + ex.getMessage());
            }
        }
        return list;
    }

    /**
     * PolymorphicTypeValidator that is used to determined whether given subtype is allowed
     * to be deserialized. Configurable standard implementation, BasicPolymorphicTypeValidator
     * is included for convenience: it supports common “allow list” approach.
     * (See <a href="https://medium.com/@cowtowncoder/jackson-2-10-features-cd880674d8a2">
     * * this article</a> for full explanation).
     */
    private static final PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfBaseType(Airplane.class).allowIfBaseType(Flight.class).allowIfBaseType(Passenger.class).allowIfBaseType(Admin.class)
            .allowIfBaseType(FlightTicket.class).allowIfBaseType(Functionality.class).allowIfBaseType(ArrayList.class)
            .allowIfBaseType(LinkedList.class).allowIfBaseType(List.class).allowIfBaseType(LocalDate.class).build();

    /**
     * Black magic to create a ObjectMapper.
     *
     * @return ObjectMapper
     */
    public static ObjectMapper getMapper() {
        if (JSON_MAPPER == null) {
            JSON_MAPPER = new ObjectMapper();
            JSON_MAPPER.registerModule(new JavaTimeModule());
            JSON_MAPPER.enable(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES);
            JSON_MAPPER.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        }
        return JSON_MAPPER;

    }
}