package pl.jsildatk.gql.syntax.sort;

import org.springframework.data.domain.Sort;

/**
 * Interface for sort syntax
 */
public interface SortSyntax {
    
    /**
     * Get sort order by field
     *
     * @param field field that query will be sorted by
     * @return sort object
     * @since 1.0.4
     */
    Sort getSort(final String field);
    
}
