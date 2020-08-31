package pl.jsildatk.gql.syntax.operator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OperatorSyntaxFactory {
    
    /**
     * Get operator syntax object based on provided operator
     *
     * @param operator operator as string
     * @return operator syntax object
     * @throws NotSupportedOperatorException if provided operator was invalid
     * @since 1.0.0
     */
    public OperatorSyntax getOperatorSyntax(String operator) {
        if ( "=".equals(operator) ) {
            return new Equals();
        } else if ( "!=".equals(operator) ) {
            return new NotEquals();
        } else if ( ">".equals(operator) ) {
            return new Greater();
        } else if ( ">=".equals(operator) ) {
            return new GreaterEquals();
        } else if ( "<".equals(operator) ) {
            return new Less();
        } else if ( "<=".equals(operator) ) {
            return new LessEquals();
        } else if ( "STARTS WITH".equalsIgnoreCase(operator) ) {
            return new StartsWith();
        } else if ( "ENDS WITH".equalsIgnoreCase(operator) ) {
            return new EndsWith();
        } else if ( "IN".equalsIgnoreCase(operator) ) {
            return new In();
        } else if ( "NOT IN".equalsIgnoreCase(operator) ) {
            return new NotIn();
        } else if ( "*".equals(operator) ) {
            return new Asterisk();
        } else if ( "BETWEEN".equalsIgnoreCase(operator) ) {
            return new Between();
        } else if ( "REGEX".equalsIgnoreCase(operator) ) {
            return new Regex();
        }
        throw new NotSupportedOperatorException(String.format("Operator: %s is not supported", operator));
    }
    
}
