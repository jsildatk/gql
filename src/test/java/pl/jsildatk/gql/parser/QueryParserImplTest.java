package pl.jsildatk.gql.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jsildatk.gql.dto.QueryDTO;
import pl.jsildatk.gql.dto.QueryRequest;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.SortPart;
import pl.jsildatk.gql.syntax.SyntaxPart;
import pl.jsildatk.gql.syntax.field.NotSupportedFieldException;
import pl.jsildatk.gql.syntax.operator.NotSupportedOperatorException;
import pl.jsildatk.gql.syntax.sort.NotSupportedSortException;

import java.util.Collections;

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
        final QueryRequest query = new QueryRequest(Collections.singletonList(part), null);
        // when
        final QueryException exception = assertThrows(NotSupportedFieldException.class, () -> queryParser.parse(query));
        // then
        assertThat(exception.getMessage(), containsString("Field: not existing field is not supported"));
    }
    
    @Test
    public void testParsingWithNoSupportedOperatorSyntax() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "=!", "asd");
        final QueryRequest query = new QueryRequest(Collections.singletonList(part), null);
        // when
        final QueryException exception = assertThrows(NotSupportedOperatorException.class, () -> queryParser.parse(query));
        // then
        assertThat(exception.getMessage(), containsString("Operator: =! is not supported"));
    }
    
    @Test
    public void testParsingWithInvalidNumericOperator() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", ">", "asd");
        final QueryRequest query = new QueryRequest(Collections.singletonList(part), null);
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> queryParser.parse(query));
        // then
        assertThat(exception.getMessage(), containsString("Numeric operator can only be used with 'year' field"));
    }
    
    @Test
    public void testParsingWithInvalidInSyntax() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "IN", "asd, asd)");
        final QueryRequest query = new QueryRequest(Collections.singletonList(part), null);
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> queryParser.parse(query));
        // then
        assertThat(exception.getMessage(), containsString("Query is invalid. Must starts with '(' and ends with ')'"));
    }
    
    @Test
    public void testParsingWithInvalidNotInSyntax() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "NOT IN", "asd, asd)");
        final QueryRequest query = new QueryRequest(Collections.singletonList(part), null);
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> queryParser.parse(query));
        // then
        assertThat(exception.getMessage(), containsString("Query is invalid. Must starts with '(' and ends with ')'"));
    }
    
    @Test
    public void testParsingWithInvalidSortOrderSyntax() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "=", "asd");
        final QueryRequest query = new QueryRequest(Collections.singletonList(part), new SortPart("A", "developer"));
        // when
        final QueryException exception = assertThrows(NotSupportedSortException.class, () -> queryParser.parse(query));
        // then
        assertThat(exception.getMessage(), containsString("Sort: A is not supported"));
    }
    
    @Test
    public void testParsingWithInvalidSortFieldSyntax() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "=", "asd");
        final QueryRequest query = new QueryRequest(Collections.singletonList(part), new SortPart("ASCENDING", "A"));
        // when
        final QueryException exception = assertThrows(NotSupportedFieldException.class, () -> queryParser.parse(query));
        // then
        assertThat(exception.getMessage(), containsString("Field: A is not supported"));
    }
    
    @Test
    public void testParsingWithValidPart() {
        // given
        final SyntaxPart part = new SyntaxPart("developer", "=", "asd");
        final QueryRequest query = new QueryRequest(Collections.singletonList(part), null);
        // when
        final QueryDTO result = queryParser.parse(query);
        // then
        assertThat(result.getCriteria()
                .getCriteriaObject()
                .containsKey("developer"), is(true));
        assertThat(result.getCriteria()
                .getCriteriaObject()
                .containsValue("asd"), is(true));
    }
    
}