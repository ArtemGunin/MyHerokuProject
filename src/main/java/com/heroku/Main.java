package com.heroku;

import java.sql.*;
import java.util.Optional;

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
        String sql = "CREATE TABLE PHONE " +
                "(id VARCHAR(255), " +
                " title VARCHAR(255), " +
                " model VARCHAR(255), " +
                " count INTEGER, " +
                " PRIMARY KEY ( id ))";

        try(final PreparedStatement statement = connection.prepareStatement(sql)) {
                System.out.println("Table created!");
                save(connection);
                printById("123", connection);
        }
    }

    private static void save(Connection connection) {
        String sql = "INSERT INTO \"public\".\"PHONE\" (id, title, model, count) VALUES (?, ?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "123");
            statement.setString(2, "Title - 1");
            statement.setString(3, "Model - 1");
            statement.setInt(4, 3);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printById(String id, Connection connection) {
        String sql = "SELECT * FROM \"public\".\"PHONE\" WHERE id = ?";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("title"));
                System.out.println(resultSet.getString("model"));
                System.out.println(resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
