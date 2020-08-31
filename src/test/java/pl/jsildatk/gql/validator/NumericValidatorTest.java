package pl.jsildatk.gql.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.field.FieldSyntax;
import pl.jsildatk.gql.syntax.field.FieldSyntaxFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class NumericValidatorTest {
    
    private OperatorValidator validator;
    
    @BeforeEach
    public void setUp() {
        validator = new NumericValidator();
    }
    
    @Test
    public void testValidatingWithInvalidNumericOperator() {
        // given
        final FieldSyntax fieldSyntax = FieldSyntaxFactory.getFieldSyntax("developer");
        final String operator = ">";
        // when
        final QueryException exception = assertThrows(QueryException.class, () -> validator.validate(fieldSyntax, operator));
        // then
        assertThat(exception.getMessage(), containsString("Numeric operator can only be used with 'year' field"));
    }
    
}