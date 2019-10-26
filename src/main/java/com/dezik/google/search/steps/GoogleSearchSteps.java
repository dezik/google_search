package com.dezik.google.search.steps;

import java.nio.charset.StandardCharsets;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.dezik.google.search.DriverFactory;
import com.dezik.google.search.pages.GoogleMainPage;
import com.dezik.google.search.pages.GoogleSearchResultsPage;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GoogleSearchSteps {
    private GoogleMainPage googleMainPage;
    private GoogleSearchResultsPage googleSearchResultsPage;

    @Before
    public void prepareBeforeTestExecution() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.pageLoadStrategy = "normal";
    }

    @Given("I have (\\w+) browser opened")
    public void openBrowser(String browserName) {
        DriverFactory.setWebDriver(browserName);
    }

    @When("I go to (.+)$")
    public void openUrl(String url) {
        googleMainPage = Selenide.open(url, GoogleMainPage.class);
    }

    @And("I type (.+) into search bar")
    public void searchFor(String searchString) {
        googleMainPage.putIntoSearchBar(searchString);
    }

    @And("I hit search")
    public void clickButton() {
        googleSearchResultsPage = googleMainPage.submitSearch();
    }

    @Then("(.+) is first url on search results page")
    public void checkUrlIsFirstOnResultsPage(String url) {
        assertThat(googleSearchResultsPage.getFirstResultUrl(), equalTo(url));
    }

    @After
    public void attachPageSourceIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.embed(getScreenshot(), "image/png");
            scenario.embed(getPageSource(), "text/html");
        }
        WebDriverRunner.getWebDriver().quit();
    }

    private byte[] getScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    private static byte[] getPageSource() {
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }
}
