package app.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    public SelenideElement searchField = $("#search");
    public SelenideElement submitButton = $("input[type='submit']");
    public SelenideElement sighInSignUpButton = $("a.popButton.login.with-modal-popup1");
    public SelenideElement profileButton = $("a.popButton.login.personalNavLoader");

    public MainPage(String pageUrl) {
        super(pageUrl);
    }

    public void searchByWord(String searchWord) {
        searchField.setValue(searchWord);
        submitButton.shouldBe(exist, enabled, visible).click();
    }
}
