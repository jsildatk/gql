package pl.jsildatk.gql.parser;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.SyntaxPart;
import pl.jsildatk.gql.syntax.field.FieldSyntax;
import pl.jsildatk.gql.syntax.field.FieldSyntaxFactory;
import pl.jsildatk.gql.syntax.field.Year;
import pl.jsildatk.gql.syntax.operator.In;
import pl.jsildatk.gql.syntax.operator.NotIn;
import pl.jsildatk.gql.syntax.operator.OperatorSyntax;
import pl.jsildatk.gql.syntax.operator.OperatorSyntaxFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QueryParserImpl implements QueryParser {
    
    @Override
    public Criteria parse(final SyntaxPart part) throws QueryException {
        log.info("Parsing: {}", part);
        
        final FieldSyntax field = FieldSyntaxFactory.getFieldSyntax(part.getField());
        final OperatorSyntax operator = OperatorSyntaxFactory.getOperatorSyntax(part.getOperator());
        final String value = part.getValue();
        
        if ( !(field instanceof Year) && isNumericOperator(operator.getOperator()) ) {
            throw new QueryException("Numeric operator can only be used with 'year' field");
        }
        
        if ( (operator instanceof In || operator instanceof NotIn) && !isValidFormat(value) ) {
            throw new QueryException("Query is invalid. Must starts with '(' and ends with ')'");
        }
        
        final Criteria main = Criteria.where(field.getField());
        if ( operator instanceof In || operator instanceof NotIn ) {
            return operator.getCriteria(main, createCollectionCriteria(value));
        }
        return operator.getCriteria(main, value);
    }
    
    private List<String> createCollectionCriteria(String value) {
        final List<String> values = new ArrayList<>();
        for ( String a : Lists.newArrayList(value.replaceAll("[()]", "")
                .split(",")) ) {
            values.add(a.trim());
        }
        return values;
    }
    
    private boolean isValidFormat(String value) {
        return value.startsWith("(") && value.endsWith(")");
    }
    
    private boolean isNumericOperator(String operator) {
        return operator.equals(">") || operator.equals(">=") || operator.equals("<") || operator.equals("<=");
    }
    
}
