package pl.jsildatk.gql.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorMessage {
    
    private final String reason;
    
}
