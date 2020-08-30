package pl.jsildatk.gql.syntax.operator;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * Interface for operator syntax
 */
public interface OperatorSyntax {
    
    /**
     * Get criteria for query (used for single operators)
     *
     * @param parent criteria first part
     * @param value  value to search for
     * @return query criteria based on operator
     * @since 1.0.0
     */
    Criteria getCriteria(final Criteria parent, final String value);
    
    /**
     * @return operator as string
     * @since 1.0.2
     */
    String getOperator();
    
    /**
     * Get criteria for query (used for collection operators)
     *
     * @param parent criteria first part
     * @param values list of values as string
     * @return query criteria based on operator
     * @since 1.0.3
     */
    Criteria getCriteria(final Criteria parent, final List<String> values);
    
}
