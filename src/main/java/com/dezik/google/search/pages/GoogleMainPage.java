package com.dezik.google.search.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class GoogleMainPage {
    private final SelenideElement searchBar = $(By.name("q"));

    public GoogleMainPage() {
        searchBar.waitUntil(Condition.exist, 5);
    }

    public GoogleMainPage putIntoSearchBar(String searchString) {
        searchBar.setValue(searchString);
        return this;
    }

    public GoogleSearchResultsPage submitSearch() {
        searchBar.sendKeys(Keys.RETURN);
        return new GoogleSearchResultsPage();
    }
}
