package pl.jsildatk.gql.syntax.operator;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * @since 1.0.7
 */
public class Regex implements OperatorSyntax {
    
    @Override
    public Criteria getCriteria(Criteria parent, String value) {
        return parent.regex(value);
    }
    
    @Override
    public String getOperator() {
        return "regex";
    }
    
    @Override
    public Criteria getCriteria(Criteria parent, List<String> values) {
        return null;
    }
    
}
