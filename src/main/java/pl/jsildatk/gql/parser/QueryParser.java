package pl.jsildatk.gql.parser;

import pl.jsildatk.gql.dto.QueryDTO;
import pl.jsildatk.gql.dto.QueryRequest;
import pl.jsildatk.gql.syntax.QueryException;

/**
 * Service for parsing query
 */
public interface QueryParser {
    
    /**
     * Parse Query request containing syntax parts and sort order
     *
     * @param query query request
     * @return dto containing criteria and sort
     * @throws QueryException if syntax part or sort order is in invalid format
     * @since 1.0.0
     */
    QueryDTO parse(final QueryRequest query) throws QueryException;
    
}
