package pl.jsildatk.gql.validator;

import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.field.FieldSyntax;

/**
 * Interface for validating operator's syntax
 */
public interface OperatorValidator {
    
    /**
     * Validate operator's syntax
     *
     * @param field field to be validated
     * @param value value to be validated (operator/value from part)
     * @throws QueryException if validation fails
     * @since 1.0.6
     */
    void validate(final FieldSyntax field, final String value) throws QueryException;
    
}
