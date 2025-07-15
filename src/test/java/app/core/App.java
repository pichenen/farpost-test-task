package app.core;

import app.pages.VdkMainPage;
import app.pages.LoginPage;
import app.pages.MainPage;
import app.pages.SearchResultsPage;

public class App {
    public LoginPage loginPage;
    public MainPage mainPage;
    public SearchResultsPage searchResultsPage;
    public VdkMainPage vdkMainPage;

    public App() {
        loginPage = PageBuilder.buildLoginPage();
        mainPage = PageBuilder.buildMainPage();
        searchResultsPage = PageBuilder.buildSearchResultsPage();
        vdkMainPage = PageBuilder.buildVdkMainPage();
    }
}
