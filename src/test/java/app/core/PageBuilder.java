package app.core;

import app.pages.VdkMainPage;
import app.pages.LoginPage;
import app.pages.MainPage;
import app.pages.SearchResultsPage;

public class PageBuilder {

    public static LoginPage buildLoginPage() {

        return new LoginPage("/sign");
    }

    public static MainPage buildMainPage() {
        return new MainPage("/");
    }

    public static SearchResultsPage buildSearchResultsPage() {

        return new SearchResultsPage("/");
    }

    public static VdkMainPage buildVdkMainPage() {
        return new VdkMainPage("/vladivostok");
    }

}
