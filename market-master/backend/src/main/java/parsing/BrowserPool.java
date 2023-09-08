package parsing;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BrowserPool {
    private final BlockingQueue<FirefoxDriver> browsers = new LinkedBlockingQueue<>();
    private final FirefoxBinary firefoxBinary = new FirefoxBinary();
    private final FirefoxOptions options = new FirefoxOptions().setBinary(firefoxBinary).setHeadless(true);
    private static final int POOL_SIZE = 5;
    private static BrowserPool singletonPool;

    public static BrowserPool getInstance() {
        if (singletonPool == null) {
            singletonPool = new BrowserPool();
        }
        return singletonPool;
    }

    public BrowserPool(){
        for (int i = 0; i < POOL_SIZE; i++) {
            new Thread(() -> {
                FirefoxDriver driver = new FirefoxDriver(options);
                browsers.add(driver);
            }).start();
        }
    }

    public FirefoxDriver getBrowser() throws InterruptedException {
        return browsers.take();
    }

    public void returnBrowser(FirefoxDriver browser){
        browsers.add(browser);
    }

    public void closeAll(){
        browsers.forEach(FirefoxDriver::quit);
        singletonPool = null;
    }
}
