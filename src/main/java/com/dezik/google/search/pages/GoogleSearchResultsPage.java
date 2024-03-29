package com.dezik.google.search.pages;

import java.util.List;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class GoogleSearchResultsPage {
    private final SelenideElement searchResultsDiv = $("#res");
    private List<SelenideElement> searchResultUrls = $$x("//a[h3]");

    public GoogleSearchResultsPage() {
        searchResultsDiv.waitUntil(Condition.visible, 5);
    }

    public String getFirstResultUrl() {
        return searchResultUrls.get(0).getAttribute("href");
    }
}
