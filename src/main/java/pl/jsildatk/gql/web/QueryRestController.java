package pl.jsildatk.gql.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.jsildatk.gql.entity.GameEntity;
import pl.jsildatk.gql.service.QueryService;
import pl.jsildatk.gql.syntax.SyntaxPart;

import java.util.List;
import static pl.jsildatk.gql.service.QueryService.FIND_MAPPING;

@RestController
@RequiredArgsConstructor
public class QueryRestController {
    
    private final QueryService queryService;
    
    @PostMapping(FIND_MAPPING)
    public List<GameEntity> findGames(@RequestBody SyntaxPart syntaxPart) {
        return queryService.getGamesFromQuery(syntaxPart);
    }
    
}
