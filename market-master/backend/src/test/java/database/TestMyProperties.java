package database;

import database.MyProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMyProperties {

    static MyProperties myProperties;

    @BeforeAll
    public static void init(){
        myProperties = new MyProperties();
    }

    @Test
    public void test(){
        assertEquals(myProperties.getHost(), "jdbc:mysql://51.250.16.106:3306/upprpo");
        assertEquals(myProperties.getLogin(), "root");
        assertEquals(myProperties.getPassword(), "root");
    }
}
