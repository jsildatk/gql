package pl.jsildatk.gql.syntax.sort;

import lombok.experimental.UtilityClass;
import pl.jsildatk.gql.syntax.field.FieldSyntaxFactory;

@UtilityClass
public class SortSyntaxFactory {
    
    /**
     * Get sort operator based on sort value. Also validate field
     *
     * @param sort sort order as string (ASCENDING, DESCENDING)
     * @return sort syntax object
     * @throws NotSupportedSortException                               if provided sort order was invalid
     * @throws pl.jsildatk.gql.syntax.field.NotSupportedFieldException if provided sort field was invalid
     * @since 1.0.4
     */
    public SortSyntax getSortSyntax(String sort, String field) {
        FieldSyntaxFactory.getFieldSyntax(field);
        
        if ( "ASCENDING".equalsIgnoreCase(sort) ) {
            return new Ascending();
        } else if ( "DESCENDING".equalsIgnoreCase(sort) ) {
            return new Descending();
        }
        throw new NotSupportedSortException(String.format("Sort: %s is not supported", sort));
    }
    
}
