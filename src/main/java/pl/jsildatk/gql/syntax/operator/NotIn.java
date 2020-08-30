package pl.jsildatk.gql.syntax.operator;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * @since 1.0.3
 */
public class NotIn implements OperatorSyntax {
    
    @Override
    public Criteria getCriteria(Criteria parent, String value) {
        return parent.not()
                .in(value);
    }
    
    @Override
    public String getOperator() {
        return "not in";
    }
    
    @Override
    public Criteria getCriteria(Criteria parent, List<String> values) {
        return parent.not()
                .in(values);
    }
    
}
