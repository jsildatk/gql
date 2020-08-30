package pl.jsildatk.gql.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.SyntaxPart;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class QueryParserImplTest {
    
    private QueryParser queryParser;
    
    @BeforeEach
    public void setUp() {
        queryParser = new QueryParserImpl();
    }
    
    @Test
    public void testParsingWithNoSupportedFieldSyntax() {
        // given
        final SyntaxPart part = new SyntaxPart("not existing field", "=", "asd");
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> queryParser.parse(part));
        // then
        assertThat(exception.getMessage(), containsString("Field: not existing field is not supported"));
    }
    
    @Test
    public void testParsingWithNoSupportedOperatorSyntax() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "=!", "asd");
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> queryParser.parse(part));
        // then
        assertThat(exception.getMessage(), containsString("Operator: =! is not supported"));
    }
    
    @Test
    public void testParsingWithInvalidNumericOperator() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", ">", "asd");
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> queryParser.parse(part));
        // then
        assertThat(exception.getMessage(), containsString("Numeric operator can only be used with 'year' field"));
    }
    
    @Test
    public void testParsingWithInvalidInSyntax() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "IN", "asd, asd)");
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> queryParser.parse(part));
        // then
        assertThat(exception.getMessage(), containsString("Query is invalid. Must starts with '(' and ends with ')'"));
    }
    
    @Test
    public void testParsingWithInvalidNotInSyntax() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "NOT IN", "asd, asd)");
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> queryParser.parse(part));
        // then
        assertThat(exception.getMessage(), containsString("Query is invalid. Must starts with '(' and ends with ')'"));
    }
    
    @Test
    public void testParsingWithValidPart() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "=", "asd");
        // when
        final Criteria result = queryParser.parse(part);
        // then
        assertThat(result.getCriteriaObject()
                .containsKey("developer"), is(true));
        assertThat(result.getCriteriaObject()
                .containsValue("asd"), is(true));
    }
    
}