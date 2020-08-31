package pl.jsildatk.gql.syntax.sort;

import pl.jsildatk.gql.syntax.QueryException;

public class NotSupportedSortException extends QueryException {
    
    public NotSupportedSortException(String message) {
        super(message);
    }
    
}
