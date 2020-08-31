package pl.jsildatk.gql.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.field.FieldSyntax;
import pl.jsildatk.gql.syntax.field.FieldSyntaxFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BetweenValidatorTest {
    
    private OperatorValidator validator;
    
    @BeforeEach
    public void setUp() {
        validator = new BetweenValidator();
    }
    
    @Test
    public void testValidatingWithInvalidBetweenField() {
        // given
        final FieldSyntax fieldSyntax = FieldSyntaxFactory.getFieldSyntax("developer");
        final String value = "23,55";
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> validator.validate(fieldSyntax, value));
        // then
        assertThat(exception.getMessage(), containsString("Between operator can only be used with 'year' field"));
    }
    
    @Test
    public void testValidatingWithInvalidBetweenValuesSize() {
        // given
        final FieldSyntax fieldSyntax = FieldSyntaxFactory.getFieldSyntax("year");
        final String value = "23,55,23";
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> validator.validate(fieldSyntax, value));
        // then
        assertThat(exception.getMessage(), containsString("Between operator supports only two values"));
    }
    
    @Test
    public void testValidatingWithInvalidBetweenSyntax() {
        // given
        final FieldSyntax fieldSyntax = FieldSyntaxFactory.getFieldSyntax("year");
        final String value = "23,asdf";
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> validator.validate(fieldSyntax, value));
        // then
        assertThat(exception.getMessage(),
                containsString("Between operator supports only numeric values and first value must not be greater than second"));
    }
    
    @Test
    public void testValidatingWithInvalidBetweenValues() {
        // given
        final FieldSyntax fieldSyntax = FieldSyntaxFactory.getFieldSyntax("year");
        final String value = "25,12";
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> validator.validate(fieldSyntax, value));
        // then
        assertThat(exception.getMessage(),
                containsString("Between operator supports only numeric values and first value must not be greater than second"));
    }
    
}