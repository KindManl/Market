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


public class TestUser {

    @Test
    public void getUserTrueTest() {
        String username;
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        username = user.getUsername();
        if (username.equals("mmikhajlov")) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void getUserFalseTest() {
        String username;
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        username = user.getUsername();
        if (username.equals("123")) {
            bool = true;
        }
        assertFalse(bool);
    }

    @Test
    public void getPassTrueTest() {
        String password;
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        password = user.getPassword();
        if (password.equals("123")) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void getPassFalseTest() {
        String password;
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        password = user.getPassword();
        if (password.equals("456")) {
            bool = true;
        }
        assertFalse(bool);
    }

    @Test
    public void getRoleTrueTest() {
        String role;
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        role = user.getRole();
        if (role.equals("admin")) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void getRoleFalseTest() {
        String role;
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        role = user.getRole();
        if (role.equals("user")) {
            bool = true;
        }
        assertFalse(bool);
    }

    @Test
    public void setUserTrueTest() {
        String username = "Dima";
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        user.setUsername(username);
        if (user.getUsername().equals("Dima")) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void setUserFalseTest() {
        String username = "Dima";
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        user.setUsername(username);
        if (user.getUsername().equals("Marat")) {
            bool = true;
        }
        assertFalse(bool);
    }

    @Test
    public void setPasswordTrueTest() {
        String password = "456";
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        user.setPassword(password);
        if (user.getPassword().equals("456")) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void setPasswordFalseTest() {
        String password = "456";
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        user.setPassword(password);
        if (user.getPassword().equals("789")) {
            bool = true;
        }
        assertFalse(bool);
    }

    @Test
    public void setRoleTrueTest() {
        String role = "user";
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        user.setRole(role);
        if (user.getRole().equals("user")) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void setRoleFalseTest() {
        String role = "user";
        boolean bool = false;
        User user = new User("mmikhajlov", "123", "admin");
        user.setRole(role);
        if (user.getRole().equals("admin")) {
            bool = true;
        }
        assertFalse(bool);
    }


}
