package me.nelson131.cwbank.database;

import java.sql.*;

import static me.nelson131.cwbank.utils.Properties.getCFG;

public class MySQL {

    private static final String url = getCFG("url");
    private static final String username = getCFG("username");
    private static final String password = getCFG("password");
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id LONG," +
                    "balance INTEGER);");
            System.out.println("table init");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createAccount(Long id, int balance) throws SQLException {
        String query = "INSERT INTO users (id, balance) VALUES (" + id + ", " + balance + ");";
        statement.executeUpdate(query);
    }

    public static void deleteAccount(Long id) throws SQLException {
        String query = "DELETE FROM users WHERE id = " + id + ";";
        statement.executeUpdate(query);
    }

    public static int getBalance(Long id) throws SQLException {
        String query = "SELECT * FROM users WHERE id=" + id + ";";
        resultSet = statement.executeQuery(query);
        if(resultSet.next()) return resultSet.getInt("balance");
        return 0;
    }

    public static void editBalance(Long id, int count) throws SQLException {
        String query = "UPDATE users SET balance = " + count + " WHERE id = " + id;
        statement.executeUpdate(query);
    }

    public static boolean checkTransferOption(Long getterId, Long senderId, int amount) throws SQLException {
        String getterQuery = "SELECT * FROM users WHERE id = " + getterId + ";";
        String senderQuery = "SELECT * FROM users WHERE id = " + senderId + ";";
        resultSet = statement.executeQuery(senderQuery);
        if(resultSet.next()){
            int balance = resultSet.getInt("balance");
            resultSet = statement.executeQuery(getterQuery);
            if(resultSet.next()){
                return balance > amount;
            }
        }
        return false;
    }

    public static void transferBalance(Long getterId, Long senderId, int amount) throws SQLException {
        String getterQuery = "SELECT * FROM users WHERE id = " + getterId + ";";
        String senderQuery = "SELECT * FROM users WHERE id = " + senderId + ";";
        resultSet = statement.executeQuery(senderQuery);
        if(resultSet.next()){
            int senderBalance = resultSet.getInt("balance");
            resultSet = statement.executeQuery(getterQuery);
            if(resultSet.next()){
                int getterBalance = resultSet.getInt("balance");

                editBalance(getterId, getterBalance+amount);
                editBalance(senderId, senderBalance-amount);
            }
        }
    }

}
