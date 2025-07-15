package app.pages;

import app.core.AppConfig;
import com.codeborne.selenide.Selenide;

public class BasePage {

    protected String pageUrl;

    public BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void open() {
        String url = AppConfig.baseUrl.trim() + pageUrl.trim();
        Selenide.open(url);
    }
}
