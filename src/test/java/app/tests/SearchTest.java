package app.tests;

import app.core.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class SearchTest extends BaseTest {

    @Test
    @DisplayName("Поиск с главной страницы")
    public void searchFromMainPageTest() {
        String searchWord = "iphone";
        int maxPrice = 1000;

        // 1. Открыть https://www.farpost.ru/vladivostok/
        app.vdkMainPage.open();
        assertUrlStartsNotWith("https://www.farpost.ru/verify");

        // 2. В строке поиска вбить "iphone" и нажать кнопку "Найти"
        app.mainPage.searchByWord(searchWord);

        // 3. Убедиться, что открыта страница с адресом
        // https://www.farpost.ru/vladivostok/tech/communication/cellphones/+/Apple+iPhone/?_suggest=1&query=iphone
        assertUrlStartsWith(
                "https://www.farpost.ru/vladivostok/tech/communication/cellphones/+/Apple+iPhone/?_suggest=1&query=iphone"
        );

        // 4. Убедиться, что на странице есть блок с подсказками
        app.searchResultsPage.suggestBlock.shouldBe(exist, enabled, visible);

        // 5. Убедиться, что в блоке три элемента и первый из них активен
        app.searchResultsPage.suggestItems.shouldHave(size(3));
        app.searchResultsPage.assertFirstSuggestItemIsActive();

        // 6. Выбрать фильтр цена и отфильтровать предложения по максимальной цене в 1000 р.
        app.searchResultsPage.goToFilterByPrice();
        app.searchResultsPage.filterByMaxPrice(maxPrice);

        // 7. Убедиться, что адрес начинается с
        // https://www.farpost.ru/vladivostok/tech/communication/cellphones/+/Apple+iPhone/? price_max=1000&query=iphone
        assertUrlStartsWith(
                "https://www.farpost.ru/vladivostok/tech/communication/cellphones/+/Apple+iPhone/?price_max=1000&query=iphone"
        );

        // 8. Убедиться, что блока из пункта 4 нет
        app.searchResultsPage.suggestBlock.shouldNotBe(visible);

        // 9. Убедиться, что на странице нет предложений дороже 1000 р.
        app.searchResultsPage.assertNoExpensiveItems(maxPrice);
    }
}
