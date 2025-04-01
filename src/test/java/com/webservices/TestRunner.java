package com.webservices;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/features", dryRun = false,
        plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber-json-report.json"}, glue = {"com.webservices.model", "com.webservices.stepDefinitions"}, tags = "@dev")


public class TestRunner {
    @Test
    public void startTest() {
        System.out.println("Starting tests....");
    }

}