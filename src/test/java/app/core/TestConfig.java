package app.core;

public class TestConfig {

    public static String browser = "chrome";
    public static String headless = "1";

    public static void initConfig() {
        browser = System.getenv("SELENIDE_BROWSER") == null ? "chrome" : System.getenv("SELENIDE_BROWSER");
        headless = System.getenv("HEADLESS") == null ? "0" : System.getenv("HEADLESS");
    }

    public static boolean isHeadless() {
        return headless.contains("1");
    }
}
