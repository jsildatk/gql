package pl.jsildatk.gql.syntax.operator;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 * @since 1.0.0
 */
public class LessEquals implements OperatorSyntax {
    
    @Override
    public Criteria getCriteria(final Criteria parent, final String value) {
        return parent.lte(value);
    }
    
    @Override
    public String getOperator() {
        return "<=";
    }
    
}
