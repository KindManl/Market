package spring;

import database.DBWorker;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import parsing.BrowserPool;
import parsing.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class Main {
    
    public static final DBWorker dbWorker = new DBWorker();

    public static void main(String[] args) {
        String driverPath = "/usr/local/bin/geckodriver";
        System.setProperty("webdriver.gecko.driver", driverPath);
        BrowserPool.getInstance();
        SpringApplication.run(Main.class, args);
    }
}
