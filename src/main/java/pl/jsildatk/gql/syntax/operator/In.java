package pl.jsildatk.gql.syntax.operator;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * @since 1.0.3
 */
public class In implements OperatorSyntax {
    
    @Override
    public Criteria getCriteria(Criteria parent, String value) {
        return parent.in(value);
    }
    
    @Override
    public String getOperator() {
        return "in";
    }
    
    @Override
    public Criteria getCriteria(Criteria parent, List<String> values) {
        return parent.in(values);
    }
    
}
