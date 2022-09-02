package com.heroku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://ec2-44-207-126-176.compute-1.amazonaws.com:5432/dfonar3j80br1r";
    private static final String USER = "yqwmwlxfngrdxs";
    private static final String PASSWORD = "65ee99abcc12509b2574314949cc3ccca0b16e821c4f9a84d11e39d8d3fae253";

    public static void main(String[] args) throws SQLException {
        System.out.println("Start program!");
        final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println(connection.getCatalog());
        System.out.println("Creating table in given database...");
        String sql = "CREATE TABLE REGISTRATION " +
                "(id VARCHAR(255), " +
                " title VARCHAR(255), " +
                " model VARCHAR(255), " +
                " count INTEGER, " +
                " PRIMARY KEY ( id ))";

        try(final PreparedStatement statement = connection.prepareStatement(sql)) {
            if (statement.execute()) {
                System.out.println("Table created!");
            } else {
                System.out.println("Table not created!");
            }
        }
    }
}
