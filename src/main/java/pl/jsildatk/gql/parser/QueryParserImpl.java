package pl.jsildatk.gql.parser;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
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
import pl.jsildatk.gql.syntax.operator.*;
import pl.jsildatk.gql.syntax.sort.SortSyntax;
import pl.jsildatk.gql.syntax.sort.SortSyntaxFactory;
import pl.jsildatk.gql.validator.OperatorValidator;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueryParserImpl implements QueryParser {
    
    private final OperatorValidator betweenValidator;
    
    private final OperatorValidator numericValidator;
    
    @Override
    public QueryDTO parse(final QueryRequest query) throws QueryException {
        log.info("Parsing: {}", query);
        
        final SyntaxPart part = query.getSyntax()
                .get(0);
        final FieldSyntax field = FieldSyntaxFactory.getFieldSyntax(part.getField());
        final OperatorSyntax operator = OperatorSyntaxFactory.getOperatorSyntax(part.getOperator());
        final String value = part.getValue();
        
        validate(operator, field, value);
        
        return new QueryDTO(resolveCriteria(operator, field, value), resolveSort(query.getSort()));
    }
    
    private void validate(OperatorSyntax operatorSyntax, FieldSyntax fieldSyntax, String value) {
        if ( operatorSyntax instanceof Between ) {
            betweenValidator.validate(fieldSyntax, value);
        }
        numericValidator.validate(fieldSyntax, operatorSyntax.getOperator());
    }
    
    private Criteria resolveCriteria(OperatorSyntax operatorSyntax, FieldSyntax fieldSyntax, String value) {
        final Criteria main = Criteria.where(fieldSyntax.getField());
        return (operatorSyntax instanceof In || operatorSyntax instanceof NotIn || operatorSyntax instanceof Between) ?
                operatorSyntax.getCriteria(main, createCollectionCriteria(value)) :
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
        for ( String a : Lists.newArrayList(value.split(",")) ) {
            values.add(a.trim());
        }
        return values;
    }
    
}
