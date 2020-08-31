package pl.jsildatk.gql.syntax;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class SortPart {
    
    private final String order;
    
    private final String field;
    
}
