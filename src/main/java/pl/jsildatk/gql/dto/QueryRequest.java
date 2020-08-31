package pl.jsildatk.gql.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.jsildatk.gql.syntax.SortPart;
import pl.jsildatk.gql.syntax.SyntaxPart;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public class QueryRequest {
    
    private final List<SyntaxPart> syntax;
    
    private final SortPart sort;
    
}
