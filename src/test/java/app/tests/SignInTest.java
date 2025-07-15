package app.tests;

import app.core.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;

public class SignInTest extends BaseTest {

    @Test
    @DisplayName("Вход на сайт")
    public void signInTest() {
        // Учетные данные для теста берем из системных пременных среды
        // для тестирования локально (без системных переменных) меняем local_test на нужные значения
        String validLogin = System.getenv("VALID_LOGIN") == null ? "local_test" : System.getenv("VALID_LOGIN");
        String validPassword = System.getenv("VALID_PASSWORD") == null ? "local_test" : System.getenv("VALID_PASSWORD");
        String invalidLogin = System.getenv("INVALID_LOGIN") == null ? "local_test" : System.getenv("INVALID_LOGIN");
        String invalidPassword = System.getenv("INVALID_PASSWORD") == null ? "local_test" : System.getenv("INVALID_PASSWORD");

        // 1. Открыть https://www.farpost.ru/
        app.mainPage.open();
        assertUrlStartsNotWith("https://www.farpost.ru/verify");

        // 2. Перейти по ссылке "Вход и регистрация"
        app.mainPage.sighInSignUpButton
                .shouldBe(exist, enabled, visible)
                .click();

        // 3. Попытаться войти с неправильными логином и паролем. Убедиться, что
        // произошла ошибка и войти не удалось
        app.loginPage.signIn(invalidLogin, invalidPassword);
        app.loginPage.signInErrorLabel.shouldBe(exist, enabled, visible);

        // 4. Войти, используя правильный логин и пароль. Убедиться, что вход успешен, открыт личный кабинет
        app.loginPage.signIn(validLogin, validPassword);
        app.mainPage.profileButton.shouldBe(exist, enabled, visible);
        app.mainPage.sighInSignUpButton.shouldNotBe(exist);
        assertUrlEquals("https://www.farpost.ru/personal/");
    }
}
