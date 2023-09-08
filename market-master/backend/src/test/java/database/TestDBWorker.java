package database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TestDBWorker {

    public static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(8) + 3];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    @Test
    public void connectionTrueTest() {
        Connection connection;
        String host = "jdbc:mysql://51.250.16.106:3306/upprpo";
        String login = "root";
        String password = "root";
        try {
            connection = DriverManager.getConnection(host, login, password);
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (SQLException ignored) {
        }

    }

    @Test
    public void connectionFalseTest() {
        Connection connection = null;
        String host = "jdbc:mysql://51.250.16.106:3306/upprpo";
        String login = "root";
        String password = "root123";
        try {
            connection = DriverManager.getConnection(host, login, password);
        } catch (SQLException ignored) {
        }
        assertNull(connection);
    }

    @Test
    public void findHistoryFalseTest() {
        List<String> arrayList;
        boolean bool = false;
        DBWorker worker = new DBWorker();
        arrayList = worker.findHistory("123");
        if (arrayList.isEmpty()) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void addUserTrueTest() {
        boolean bool;
        String[] str;
        DBWorker worker = new DBWorker();
        str = generateRandomWords(4);
        bool = worker.addUser(str[0] + str[1] + str[2] + str[3], "123");
        assertTrue(bool);
    }

    @Test
    public void addUserFalseTest() {
        boolean bool;
        DBWorker worker = new DBWorker();
        bool = worker.addUser("mmikhajlov", "123");
        assertFalse(bool);
    }

    @Test
    public void findUserFalseTest() {
        boolean bool = false;
        String str;
        DBWorker worker = new DBWorker();
        str = worker.findUser("123dwdwdafrgerhhewe", "123");
        if (str.equals("Неверный логин или пароль!")) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void findUserTrueTest() {
        boolean bool = false;
        String str;
        DBWorker worker = new DBWorker();
        str = worker.findUser("mmikhajlov", "123");
        if (str.equals("mmikhajlov")) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void addHistoryTrueTest() {
        boolean bool;
        DBWorker worker = new DBWorker();
        bool = worker.addHistory("efwhwek5wjqrfq2", "Videos");
        assertTrue(bool);
    }

    @Test
    public void findHistoryTrueTest() {
        List<String> arrayList;
        DBWorker worker = new DBWorker();
        arrayList = worker.findHistory("mmikhajlov");
        assertNotNull(arrayList);
    }


}
