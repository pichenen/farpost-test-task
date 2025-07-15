package app.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    public SelenideElement loginField = $("#sign");
    public SelenideElement passwordField = $("#password");
    public SelenideElement submitSignInButton = $("#signbutton");
    public SelenideElement signInErrorLabel = $("#sign_errors");

    public LoginPage(String pageUrl) {
        super(pageUrl);
    }

    public void signIn(String login, String password) {
        loginField
                .shouldBe(exist, enabled, visible)
                .setValue(login);

        passwordField
                .shouldBe(exist, enabled, visible)
                .setValue(password);

        submitSignInButton
                .shouldBe(exist, enabled, visible)
                .click();
    }
}
