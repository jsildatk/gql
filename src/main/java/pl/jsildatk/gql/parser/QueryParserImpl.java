package pl.jsildatk.gql.parser;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import pl.jsildatk.gql.dto.QueryDTO;
import pl.jsildatk.gql.dto.QueryRequest;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.SortPart;
import pl.jsildatk.gql.syntax.SyntaxPart;
import pl.jsildatk.gql.syntax.field.FieldSyntax;
import pl.jsildatk.gql.syntax.field.FieldSyntaxFactory;
import pl.jsildatk.gql.syntax.field.Year;
import pl.jsildatk.gql.syntax.operator.In;
import pl.jsildatk.gql.syntax.operator.NotIn;
import pl.jsildatk.gql.syntax.operator.OperatorSyntax;
import pl.jsildatk.gql.syntax.operator.OperatorSyntaxFactory;
import pl.jsildatk.gql.syntax.sort.SortSyntax;
import pl.jsildatk.gql.syntax.sort.SortSyntaxFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QueryParserImpl implements QueryParser {
    
    @Override
    public QueryDTO parse(final QueryRequest query) throws QueryException {
        log.info("Parsing: {}", query);
        
        final SyntaxPart part = query.getSyntax()
                .get(0);
        final FieldSyntax field = FieldSyntaxFactory.getFieldSyntax(part.getField());
        final OperatorSyntax operator = OperatorSyntaxFactory.getOperatorSyntax(part.getOperator());
        final String value = part.getValue();
        
        if ( !(field instanceof Year) && isNumericOperator(operator.getOperator()) ) {
            throw new QueryException("Numeric operator can only be used with 'year' field");
        }
        
        if ( (operator instanceof In || operator instanceof NotIn) && !isValidFormat(value) ) {
            throw new QueryException("Query is invalid. Must starts with '(' and ends with ')'");
        }
        
        return new QueryDTO(resolveCriteria(operator, field, value), resolveSort(query.getSort()));
    }
    
    private Criteria resolveCriteria(OperatorSyntax operatorSyntax, FieldSyntax fieldSyntax, String value) {
        final Criteria main = Criteria.where(fieldSyntax.getField());
        return (operatorSyntax instanceof In || operatorSyntax instanceof NotIn) ? operatorSyntax.getCriteria(main, createCollectionCriteria(value)) :
                operatorSyntax.getCriteria(main, value);
    }
    
    private Sort resolveSort(SortPart sortPart) {
        if ( sortPart == null ) {
            return null;
        }
        final SortSyntax sortSyntax = SortSyntaxFactory.getSortSyntax(sortPart.getOrder(), sortPart.getField());
        return sortSyntax.getSort(sortPart.getField());
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
