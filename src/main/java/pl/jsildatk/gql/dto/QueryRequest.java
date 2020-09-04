package pl.jsildatk.gql.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.jsildatk.gql.syntax.SortPart;
import pl.jsildatk.gql.syntax.SyntaxPart;

@RequiredArgsConstructor
@Getter
@ToString
public class QueryRequest {
    
    private final SyntaxPart syntax;
    
    private final SortPart sort;
    
}
