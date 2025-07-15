package app.core;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.open;

public class Driver {

    public static void initDriver() {
        TestConfig.initConfig();

        Configuration.remote = System.getenv("SELENIDE_REMOTE");
        Configuration.browserSize = "1920x1080";
        Configuration.headless = TestConfig.isHeadless();

        switch (TestConfig.browser)
        {
            case "chrome":
                Configuration.browser = Browsers.CHROME;
                break;
            case "firefox":
                Configuration.browser = Browsers.FIREFOX;
                break;
            default:
                Configuration.browser = Browsers.CHROME;
                break;
        }
    }

    public static void clearCookies() {
        open(AppConfig.baseUrl);
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public static void close() {
        Selenide.closeWebDriver();
    }
}
