package pl.jsildatk.gql.parser;

import org.springframework.data.mongodb.core.query.Criteria;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.SyntaxPart;

/**
 * Service for parsing query
 */
public interface QueryParser {
    
    /**
     * Parse single query's part
     *
     * @param part syntax part
     * @return database criteria
     * @throws QueryException if part is in invalid format
     * @since 1.0.0
     */
    Criteria parse(final SyntaxPart part) throws QueryException;
    
}
