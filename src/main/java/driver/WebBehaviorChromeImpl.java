package driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.Properties;

import java.util.concurrent.TimeUnit;

public class WebBehaviorChromeImpl extends ChromeDriver implements WebBehavior {

    public WebBehaviorChromeImpl() {
        super.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        super.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        super.manage().window().maximize();
    }

    static {
        System.setProperty("webdriver.chrome.driver", Properties.getInstance().getProperties().getProperty("webdriver.chrome.driver"));
    }

    @Override
    public RemoteWebDriver getDriver() {
        return this;
    }

    @Override
    public void closeAndQuit() {
        this.close();
        this.quit();
    }
}
