import com.codeborne.selenide.Selenide;
import driver.DriverProvider;
import driver.WebBehavior;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class SelenoidTest {
    static WebBehavior driver;

    @Before
    public void initDriver() {
        driver = new DriverProvider().getDriver();
    }

    @Test
    public void initDriverTest() {
        driver.get("https://mvnrepository.com/");
    }

    @Test
    public void selenoidTest() throws InterruptedException {
//        org.apache.log4j.BasicConfigurator.configure();
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

    @AfterEach
    public void stopDriver() {
        driver.closeAndQuit();
    }
}
