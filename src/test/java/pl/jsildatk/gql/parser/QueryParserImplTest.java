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
import pl.jsildatk.gql.validator.OperatorValidator;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.mock;

public class QueryParserImplTest {
    
    private QueryParser queryParser;
    
    @BeforeEach
    public void setUp() {
        OperatorValidator betweenValidator = mock(OperatorValidator.class);
        OperatorValidator numericValidator = mock(OperatorValidator.class);
        queryParser = new QueryParserImpl(betweenValidator, numericValidator);
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