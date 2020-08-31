package pl.jsildatk.gql.syntax.sort;

import org.springframework.data.domain.Sort;

/**
 * @since 1.0.4
 */
public class Ascending implements SortSyntax {
    
    @Override
    public Sort getSort(String field) {
        return Sort.by(new Sort.Order(Sort.Direction.ASC, field.toLowerCase()));
    }
    
}
