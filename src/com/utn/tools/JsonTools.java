package com.utn.tools;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * JsonTools use <b>Jackson library</b>
 * to serialize Java objects into JSON
 * and deserialize JSON into Java objects
 */
public class JsonTools {
    public static final String fpassengers = "resources/passengers.json";
    public static final String fairplanes = "resources/airplanes.json";
    public static final String fflights = "resources/flights.json";

    //Constant of a ObjectMapper
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    /**
     * Write a JSON file of a given list of Objects
     *
     * @param list {@link List} The source list
     * @param path {@link String}Path of the file
     */
    public static <t> void writePassengerJson(List<t> list, String path) {
        if (list == null) System.out.println("E R R O R passengers cant be null");
        else {
            OutputStream buffer = null;
            try {
                buffer = new FileOutputStream(new File(path));
            } catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
            try {
                JSON_MAPPER.writeValue(buffer, list);
                buffer.close();
            } catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
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
    public static <T> List<T> readPassengerJson(String path, Class<T> tClass) {
        InputStream buffer = null;
        try {
            buffer = new FileInputStream(new File(path));
        } catch (IOException ex) {
            System.out.println("E R R O R : " + ex.getMessage());
        }

        List<T> list = null;
        if (buffer != null) {
            try {
                //passengers = JSON_MAPPER.readValue(buffer, new TypeReference<LinkedList<Passenger>>() {
                list = JSON_MAPPER.readValue(buffer,
                        JSON_MAPPER.getTypeFactory().constructCollectionType(LinkedList.class, tClass));
            } catch (IOException | IllegalArgumentException ex) {
                System.out.println("E R R O R : " + ex.getMessage());
            }
        }
        return list;
    }
}
