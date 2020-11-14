package driver;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WebBehaviorSelenoidImpl extends RemoteWebDriver implements WebBehavior {
    private static java.util.Properties properties = util.Properties.getInstance().getProperties();
    RemoteWebDriver driver;
    static DesiredCapabilities capabilities;

    public WebBehaviorSelenoidImpl() throws MalformedURLException {
        super(new URL((String) properties.get("selenoid.host")), capabilities);
        WebDriverRunner.setWebDriver(this);
        this.manage().window().setSize(new Dimension(1920, 1080));
    }

    static {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "84.0");
        Map<String, Object> cap = new HashMap<>();
        cap.put("enableVNC", true);
        cap.put("enableVideo", true);
        capabilities.setCapability("selenoid:options", cap);
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    @Override
    public void closeAndQuit() {
        Optional.ofNullable(WebDriverRunner.getWebDriver()).ifPresent(WebDriver::close);
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }
}
