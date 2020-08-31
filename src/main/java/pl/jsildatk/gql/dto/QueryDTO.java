package pl.jsildatk.gql.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor
@Getter
public class QueryDTO {
    
    private final Criteria criteria;
    
    private final Sort sort;
    
}
