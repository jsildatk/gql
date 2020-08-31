package pl.jsildatk.gql.validator;

import org.springframework.stereotype.Service;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.field.FieldSyntax;
import pl.jsildatk.gql.syntax.field.Year;

/**
 * @since 1.0.6
 */
@Service
public class NumericValidator implements OperatorValidator {
    
    @Override
    public void validate(FieldSyntax field, String value) throws QueryException {
        if ( !(field instanceof Year) && isNumericOperator(value) ) {
            throw new QueryException("Numeric operator can only be used with 'year' field");
        }
    }
    
    private boolean isNumericOperator(String operator) {
        return operator.equals(">") || operator.equals(">=") || operator.equals("<") || operator.equals("<=");
    }
    
}
