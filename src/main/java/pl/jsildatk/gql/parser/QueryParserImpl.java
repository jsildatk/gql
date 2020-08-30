package pl.jsildatk.gql.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import pl.jsildatk.gql.syntax.SyntaxPart;
import pl.jsildatk.gql.syntax.field.FieldSyntax;
import pl.jsildatk.gql.syntax.field.FieldSyntaxFactory;
import pl.jsildatk.gql.syntax.operator.OperatorSyntax;
import pl.jsildatk.gql.syntax.operator.OperatorSyntaxFactory;

@Service
@Slf4j
public class QueryParserImpl implements QueryParser {
    
    @Override
    public Criteria parse(final SyntaxPart part) {
        log.info("Parsing: {}", part);
        
        final FieldSyntax field = FieldSyntaxFactory.getFieldSyntax(part.getField());
        final OperatorSyntax operator = OperatorSyntaxFactory.getOperatorSyntax(part.getOperator());
        final String value = part.getValue();
        
        final Criteria main = Criteria.where(field.getField());
        return operator.getCriteria(main, value);
    }
    
}
