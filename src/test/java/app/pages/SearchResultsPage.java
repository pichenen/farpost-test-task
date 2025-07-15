package app.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage extends BasePage {

    public SelenideElement suggestBlock = $("div.suggestionsList");
    public ElementsCollection suggestItems = $$(".dir-search-suggest__item");
    public SelenideElement filterGroupPrice = $("a[data-url-label='cena']");
    public SelenideElement filterInputMaxPrice = $("input[name='price_max']");
    public SelenideElement saveSearchButton = $("button[data-action='save-search']");
    public ElementsCollection itemsPrices = $$("div.price-block__price");

    public SearchResultsPage(String pageUrl) {
        super(pageUrl);
    }

    public void assertFirstSuggestItemIsActive() {
        suggestItems
                .first()
                .shouldBe(cssClass("dir-search-suggest__item_active"));
    }

    public void goToFilterByPrice() {
        filterGroupPrice
                .shouldBe(exist, enabled, visible)
                .click();
    }

    public void filterByMaxPrice(Integer maxPrice) {
        filterInputMaxPrice
                .shouldBe(exist, enabled, visible)
                .setValue(maxPrice.toString())
                .pressEnter();
    }

    public void assertNoExpensiveItems(Integer maxPrice) {
        itemsPrices.first().shouldBe(visible, Duration.ofSeconds(5));
        saveSearchButton.shouldBe(exist, enabled, visible);
        boolean hasNoHighPrice = itemsPrices
                .asDynamicIterable()
                .stream()
                .noneMatch(price -> {
                    String priceText = price.getText().replaceAll("[^0-9.]","");
                    return !priceText.isEmpty() && Double.parseDouble(priceText) > maxPrice;
                });
        Assertions.assertTrue(hasNoHighPrice, "Найдена цена > " + maxPrice);
    }
}
