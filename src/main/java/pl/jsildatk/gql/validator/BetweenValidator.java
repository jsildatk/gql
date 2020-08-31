package pl.jsildatk.gql.validator;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.field.FieldSyntax;
import pl.jsildatk.gql.syntax.field.Year;

import java.util.List;

/**
 * @since 1.0.6
 */
@Service
public class BetweenValidator implements OperatorValidator {
    
    @Override
    public void validate(final FieldSyntax field, final String value) throws QueryException {
        if ( !(field instanceof Year) ) {
            throw new QueryException("Between operator can only be used with 'year' field");
        } else {
            final List<String> values = Lists.newArrayList(value.split(","));
            if ( values.size() != 2 ) {
                throw new QueryException("Between operator supports only two values");
            } else if ( !isValidFormat(values) ) {
                throw new QueryException("Between operator supports only numeric values and first value must not be greater than second");
            }
        }
    }
    
    private boolean isValidFormat(List<String> values) {
        for ( String a : values ) {
            if ( !StringUtils.isNumeric(a.trim()) ) {
                return false;
            }
        }
        return Integer.parseInt(values.get(0)
                .trim()) <= Integer.parseInt(values.get(1)
                .trim());
    }
    
}
