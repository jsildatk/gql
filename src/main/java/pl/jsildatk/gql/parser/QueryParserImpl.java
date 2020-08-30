package pl.jsildatk.gql.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.SyntaxPart;
import pl.jsildatk.gql.syntax.field.FieldSyntax;
import pl.jsildatk.gql.syntax.field.FieldSyntaxFactory;
import pl.jsildatk.gql.syntax.operator.OperatorSyntax;
import pl.jsildatk.gql.syntax.operator.OperatorSyntaxFactory;

@Service
@Slf4j
public class QueryParserImpl implements QueryParser {
    
    @Override
    public Criteria parse(final SyntaxPart part) throws QueryException {
        log.info("Parsing: {}", part);
        
        final FieldSyntax field = FieldSyntaxFactory.getFieldSyntax(part.getField());
        final OperatorSyntax operator = OperatorSyntaxFactory.getOperatorSyntax(part.getOperator());
        final String value = part.getValue();
        
        if ( !field.getField()
                .equalsIgnoreCase("YEAR") && isNumericOperator(operator.getOperator()) ) {
            throw new QueryException("Numeric operator can only be used with 'year' field");
        }
        
        final Criteria main = Criteria.where(field.getField());
        return operator.getCriteria(main, value);
    }
    
    private boolean isNumericOperator(String operator) {
        return operator.equals(">") || operator.equals(">=") || operator.equals("<") || operator.equals("<=");
    }
    
}
