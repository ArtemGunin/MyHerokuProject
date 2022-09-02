package com.heroku;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:postgresql://ec2-44-207-126-176.compute-1.amazonaws.com:5432/dfonar3j80br1r";
    private static final String USER = "yqwmwlxfngrdxs";
    private static final String PASSWORD = "65ee99abcc12509b2574314949cc3ccca0b16e821c4f9a84d11e39d8d3fae253";

    public static void main(String[] args) throws SQLException {
        String id = "123";
        System.out.println("Start program!");
        final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Creating table in given database...");
        String sql = "CREATE TABLE new_phone_table1" +
                "(id VARCHAR(255), " +
                " title VARCHAR(255), " +
                " model VARCHAR(255), " +
                " count INTEGER, " +
                " PRIMARY KEY ( id ))";


        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            System.out.println("Table created!");
            System.out.println("Insert values: \nid = 123\ntitle = Title - 1\nmodel = Model - 1\ncount = 3");
            save(connection);
            System.out.println("\nGet values:");
            printById(id, connection);
        }
    }

    private static void save(Connection connection) {
        String sql = "INSERT INTO \"new_phone_table1\" (id, title, model, count) VALUES (?, ?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "123");
            statement.setString(2, "Title - 1");
            statement.setString(3, "Model - 1");
            statement.setInt(4, 3);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printById(String id, Connection connection) {
        String sql = "SELECT * FROM \"new_phone_table1\" WHERE id = ?";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id = " + resultSet.getString("id"));
                System.out.println("title = " + resultSet.getString("title"));
                System.out.println("model = " + resultSet.getString("model"));
                System.out.println("count = " + resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

//COMMANDS

//    heroku login
//
//    heroku git:remote -a limitless-atoll-52560
//
//        git commit -am "DB"
//
//        git push heroku master
//
//        heroku run bash
//
//        cd target/
//
//        java -jar HerokuProject-1.0-SNAPSHOT.jar
//
//        exit