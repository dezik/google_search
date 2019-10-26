package com.dezik.google.search;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "pretty"},
        features = "src/main/resources/features",
        glue = "com.dezik.google.search.steps"
)
public class TestRunner {
}
