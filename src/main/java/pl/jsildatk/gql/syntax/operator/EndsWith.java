package pl.jsildatk.gql.syntax.operator;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * @since 1.0.1
 */
public class EndsWith implements OperatorSyntax {
    
    @Override
    public Criteria getCriteria(Criteria parent, String value) {
        return parent.regex(value + "$");
    }
    
    @Override
    public String getOperator() {
        return "$";
    }
    
    @Override
    public Criteria getCriteria(Criteria parent, List<String> values) {
        return null;
    }
    
}
