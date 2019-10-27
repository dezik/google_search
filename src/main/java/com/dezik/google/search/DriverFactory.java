package com.dezik.google.search;

import java.util.HashMap;
import java.util.Map;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class DriverFactory {
    public static void setWebDriver(String driverName) {
        switch (driverName.toLowerCase()) {
            case "firefox":
                Configuration.browser = "firefox";
                break;
            case "mobile": {
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "Nexus 5");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                WebDriverRunner.setWebDriver(new ChromeDriver(chromeOptions));
                break;
            }
            case "chrome":
            default:
                Configuration.browser = "chrome";
        }
    }
}
