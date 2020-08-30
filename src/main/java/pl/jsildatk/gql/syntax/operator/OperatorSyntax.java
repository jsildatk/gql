package pl.jsildatk.gql.syntax.operator;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 * Interface for operator syntax
 */
public interface OperatorSyntax {
    
    /**
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
    
}
