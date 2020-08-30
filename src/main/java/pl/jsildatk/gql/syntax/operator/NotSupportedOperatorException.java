package pl.jsildatk.gql.syntax.operator;

import pl.jsildatk.gql.syntax.QueryException;

public class NotSupportedOperatorException extends QueryException {
    
    public NotSupportedOperatorException(String message) {
        super(message);
    }
    
}
