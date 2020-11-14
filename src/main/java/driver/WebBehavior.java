package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebBehavior extends WebDriver {
    RemoteWebDriver getDriver();

    void closeAndQuit();
}
