package parsing;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.script.ScriptException;
import java.io.*;
import java.net.*;
import java.util.List;

public abstract class Parser {

    static Logger log = LoggerFactory.getLogger(Parser.class);
    // Парсинг информации о продукте из файла html

    // Поиск товаров по заданной строке и формирование списка с товарами
    public abstract List<Product> search(String str);


    public static String getUrlContent(String link) throws IOException {
        return getUrlContent(new URL(link));
    }

    public static String getUrlContent(URL url) {
        String content = "";
        try {
            log.info("Trying to get browser");
            FirefoxDriver driver = BrowserPool.getInstance().getBrowser();
            log.info("Got browser: " + driver.toString());
            driver.get(url.toString());
            content = driver.getPageSource();
            BrowserPool.getInstance().returnBrowser(driver);
        } catch (InterruptedException e) {
            log.error("InterruptedException: ", e);
            Thread.currentThread().interrupt();
        }
        return content;
    }
}
