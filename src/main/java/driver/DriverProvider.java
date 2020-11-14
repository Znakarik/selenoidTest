package driver;

import java.net.MalformedURLException;

public class DriverProvider {
    java.util.Properties properties = util.Properties.getInstance().getProperties();
    WebBehavior driver;

    public DriverProvider() {
        if (!properties.get("webdriver.chrome.driver").toString().isEmpty()) {
            driver = new WebBehaviorChromeImpl();
        } else if (!properties.get("selenoid.host").toString().isEmpty()) {
            try {
                driver = new WebBehaviorSelenoidImpl();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public WebBehavior getDriver() {
        return driver;
    }

}
