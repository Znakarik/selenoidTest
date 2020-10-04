import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class SelenoidTest {

    @Before
    public void initDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "84.0");
        Map<String,Object> cap = new HashMap<>();
        cap.put("enableVNC", true);
        cap.put("enableVideo", true);
        capabilities.setCapability("selenoid:options", cap);

        final String remoteDriver = "http://localhost:4444/wd/hub";
        WebDriver driver = new RemoteWebDriver(new URL(remoteDriver), capabilities);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        WebDriverRunner.setWebDriver(driver);
    }

    @Test
    public void test() throws InterruptedException {
        org.apache.log4j.BasicConfigurator.configure();
        step("Открываем maven central", () -> {
            Selenide.open("https://mvnrepository.com/");
        });
        step("Вводим в поле поиска junit", () ->
                $x("//input[@class='textfield']").sendKeys("junit"));
        Thread.sleep(3000);
        step("Кликаем по кнопке search", () ->
                $x("//input[@class='button']").click());
        step("Кликаем по первой ссылке", () ->
                $x("//a[text()='JUnit Jupiter API']").click());
        step("Кликаем по последней версии", () ->
                $x("//a[text()='5.7.0']").click());
        step("Кликаем по области с зависимостью", () ->
                $x("//textarea[@id='maven-a']").click());
        step("Созраняем и печатем зависимость", () ->
                System.out.println($x("//textarea[@id='maven-a']").getText()));
    }

    @After
    public void stopDriver(){
        Optional.ofNullable(WebDriverRunner.getWebDriver()).ifPresent(WebDriver::close);
    }
}
