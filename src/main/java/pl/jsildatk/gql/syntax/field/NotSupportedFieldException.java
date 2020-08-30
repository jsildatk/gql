package pl.jsildatk.gql.syntax.field;

import pl.jsildatk.gql.syntax.QueryException;

public class NotSupportedFieldException extends QueryException {
    
    public NotSupportedFieldException(String message) {
        super(message);
    }
    
}
