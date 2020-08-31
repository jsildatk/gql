package pl.jsildatk.gql.syntax.operator;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * @since 1.0.6
 */
public class Between implements OperatorSyntax {
    
    @Override
    public Criteria getCriteria(Criteria parent, String value) {
        return null;
    }
    
    @Override
    public String getOperator() {
        return "between";
    }
    
    @Override
    public Criteria getCriteria(Criteria parent, List<String> values) {
        return parent.gte(values.get(0))
                .lte(values.get(1));
    }
    
}
