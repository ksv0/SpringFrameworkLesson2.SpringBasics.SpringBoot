package ru.gb.my_first_crud.service;

import java.io.IOException;
import java.util.Properties;
@Deprecated
public class StringService {
    private static final Properties props = new Properties();
    /**
     * SELECT * FROM %s
     */
    public static final String SELECT_ALL_FROM = "SELECT_ALL_FROM";
    /**
     * INSERT INTO %s (%s,%s) VALUES ( ?, ?)
     */
    public static final String INSERT_INTO = "INSERT_INTO";
    /**
     * DELETE FROM %s WHERE %s=?
     */
    public static final String DELETE_FROM = "DELETE_FROM";
    /**
     * UPDATE %s SET %s = ?, %s = ? WHERE %s = ?
     */
    public static final String UPDATE = "UPDATE";
    /**
     * SELECT * FROM %s WHERE %s = ?
     */
    public static final String SELECT_ALL_FROM_WHERE = "SELECT_ALL_FROM_WHERE";

    /**
     * userTable
     */
    public static final String TABLE = "TABLE";
    /**
     * id
     */
    public static final String ID = "ID";
    /**
     * firstName
     */
    public static final String FIRST_NAME = "FIRST_NAME";
    /**
     * lastName
     */
    public static final String LAST_NAME = "LAST_NAME";


    public static String get(String key) {
        try {
            props.load(StringService.class.getClassLoader().getResourceAsStream("config.properties"));
            return props.getProperty(key);
        } catch (IOException e) {
            return "Not found";
        }
    }

    public static void main(String[] args) {

        System.out.println(StringService.get(StringService.SELECT_ALL_FROM));
    }

}
