package app.core;

import com.codeborne.selenide.WebDriverConditions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.webdriver;

public class BaseTest {

    protected static App app;

    @BeforeAll
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterAll
    public static void clearCookies() {
        Driver.clearCookies();
    }

    @BeforeEach
    public void setUp() {
        Driver.initDriver();
        app = new App();
    }

    @AfterEach
    public void tearDown() {
        Driver.close();
    }

    public void assertUrlStartsWith(String url) {
        webdriver().shouldHave(WebDriverConditions.urlStartingWith(url));
    }

    public void assertUrlStartsNotWith(String url) {
        webdriver().shouldNotHave(WebDriverConditions.urlStartingWith(url));
    }

    public void assertUrlEquals(String url) {
        webdriver().shouldHave(WebDriverConditions.url(url));
    }
}
