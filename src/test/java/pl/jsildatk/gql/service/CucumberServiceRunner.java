package pl.jsildatk.gql.service;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "pl.jsildatk", plugin = { "pretty", "json:target/cucumber/service.json" },
        features = "src/test/resources/pl/jsildatk/gql/service")
public class CucumberServiceRunner {
}
