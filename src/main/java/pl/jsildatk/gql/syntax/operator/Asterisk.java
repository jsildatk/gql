package pl.jsildatk.gql.syntax.operator;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * @since 1.0.5
 */
public class Asterisk implements OperatorSyntax {
    
    @Override
    public Criteria getCriteria(Criteria parent, String value) {
        return parent.regex("^");
    }
    
    @Override
    public String getOperator() {
        return "*";
    }
    
    @Override
    public Criteria getCriteria(Criteria parent, List<String> values) {
        return parent.regex("^");
    }
    
}
