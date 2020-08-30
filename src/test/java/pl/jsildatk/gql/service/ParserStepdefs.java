package pl.jsildatk.gql.service;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.jsildatk.gql.GameDTO;
import pl.jsildatk.gql.syntax.SyntaxPart;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;

public class ParserStepdefs extends AbstractStepdef {
    
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @Given("^Data$")
    public void data(List<GameDTO> games) {
        loadData(games);
    }
    
    @Given("^I am looking for a \"([^\"]*)\" field with \"([^\"]*)\" operator and \"([^\"]*)\" value$")
    public void iAmLookingForAFieldWithOperatorAndValue(String field, String operator, String value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }
    
    @When("^I am parsing query$")
    public void iAmParsingQuery() {
        result = convertToDTO(queryService.getGamesFromQuery(new SyntaxPart(field, operator, value)));
    }
    
    @Then("^Result contains$")
    public void resultContains(List<GameDTO> expected) {
        assertThat(result, equalTo(expected));
    }
    
    @Then("^Result is empty$")
    public void resultIsEmpty() {
        assertThat(result, emptyIterable());
    }
    
}
