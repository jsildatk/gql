package pl.jsildatk.gql.syntax.field;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FieldSyntaxFactory {
    
    /**
     * Get field syntax object based on provided field
     *
     * @param field field as string
     * @return field syntax object
     * @throws NotSupportedFieldException if provided field was invalid
     * @since 1.0.0
     */
    public FieldSyntax getFieldSyntax(String field) {
        if ( "PUBLISHER".equalsIgnoreCase(field) ) {
            return new Publisher();
        } else if ( "DEVELOPER".equalsIgnoreCase(field) ) {
            return new Developer();
        } else if ( "GENRE".equalsIgnoreCase(field) ) {
            return new Genre();
        } else if ( "YEAR".equalsIgnoreCase(field) ) {
            return new Year();
        } else if ( "TITLE".equalsIgnoreCase(field) ) {
            return new Title();
        }
        throw new NotSupportedFieldException(String.format("Field: %s is not supported", field));
    }
    
}
