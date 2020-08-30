package pl.jsildatk.gql.syntax;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class SyntaxPart {
    
    private final String field;
    
    private final String operator;
    
    private final String value;
    
}
