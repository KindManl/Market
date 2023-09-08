package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.*;
import database.MyProperties;

public class DBWorker {
    private static Connection connection;
    static Logger logger;

    String host;
    String login;
    String password;

    @Autowired
    private MyProperties myProperties;

    public DBWorker() {
        try {
            myProperties = new MyProperties();
            host = myProperties.getHost();
            login = myProperties.getLogin();
            password = myProperties.getPassword();
            connection = DriverManager.getConnection(host, login, password);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Connection is false!");
            System.exit(1);
        }
    }

    public Boolean addUser(String username, String password) {
        PreparedStatement preparedStatementInsert = null;
        try {
            String insert = "insert into  users (username, password, last_date) values  (?, ?, CURRENT_TIMESTAMP)";
            preparedStatementInsert = connection.prepareStatement(insert);
            preparedStatementInsert.setString(1, username);
            preparedStatementInsert.setString(2, password);
            preparedStatementInsert.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (preparedStatementInsert != null) {
                    preparedStatementInsert.close();
                }
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Can't close statement!");
            }
        }
    }

    public String findUser(String username, String password) {
        String query = "select username from users where username = ? and password = ?";
        String update = "update users set last_date = current_timestamp where username = ?";
        String result;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatementUpdate = connection.prepareStatement(update);
             PreparedStatement preparedStatementQuery = connection.prepareStatement(query);
        ) {
            preparedStatementQuery.setString(1, username);
            preparedStatementQuery.setString(2, password);
            resultSet = preparedStatementQuery.executeQuery();
            resultSet.next();
            result = resultSet.getString(1);
            preparedStatementUpdate.setString(1, result);
            preparedStatementUpdate.executeUpdate();
            return result;
        } catch (SQLException e) {
            return "Неверный логин или пароль!";
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ignored) {
            }
        }
    }

    public Boolean addHistory(String username, String content) {
        String insert = "insert into search_history (username, content, content_date) values (?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement preparedStatementInsert = connection.prepareStatement(insert)) {
            preparedStatementInsert.setString(1, username);
            preparedStatementInsert.setString(2, content);
            preparedStatementInsert.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<String> findHistory(String username) {
        List<String> results;
        ResultSet resultSet = null;
        String query = "select distinct content from search_history where username = ?";
        try (PreparedStatement preparedStatementQuery = connection.prepareStatement(query)) {
            results = new ArrayList<>();
            preparedStatementQuery.setString(1, username);
            resultSet = preparedStatementQuery.executeQuery();
            while (resultSet.next())
                results.add(resultSet.getString(1));
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ignored) {
            }
        }
        return results;
    }

}
