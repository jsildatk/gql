package pl.jsildatk.gql.service;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.jsildatk.gql.GameDTO;
import pl.jsildatk.gql.dto.QueryRequest;
import pl.jsildatk.gql.syntax.SortPart;
import pl.jsildatk.gql.syntax.SyntaxPart;

import java.util.Collections;
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
        this.sortOrder = null;
        this.sortField = null;
    }
    
    @Given("^I am looking for a \"([^\"]*)\" field with \"([^\"]*)\" operator and \"([^\"]*)\" value and \"([^\"]*)\" sort by \"([^\"]*)\" field$")
    public void iAmLookingForAFieldWithOperatorAndValueAndSortByField(String field, String operator, String value, String sortOrder,
            String sortField) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.sortOrder = sortOrder;
        this.sortField = sortField;
    }
    
    @When("^I am parsing query$")
    public void iAmParsingQuery() {
        if ( sortOrder == null && sortField == null ) {
            result = convertToDTO(
                    queryService.getGamesFromQuery(new QueryRequest(Collections.singletonList(new SyntaxPart(field, operator, value)), null)));
        } else {
            result = convertToDTO(
                    queryService.getGamesFromQuery(
                            new QueryRequest(Collections.singletonList(new SyntaxPart(field, operator, value)), new SortPart(sortOrder, sortField))));
        }
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
