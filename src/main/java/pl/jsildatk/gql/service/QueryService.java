package pl.jsildatk.gql.service;

import pl.jsildatk.gql.entity.GameEntity;
import pl.jsildatk.gql.syntax.SyntaxPart;

import java.util.List;

/**
 * Interface for handling user's queries
 */
public interface QueryService {
    
    String FIND_MAPPING = "/api/find";
    
    /**
     * @param syntaxPart syntax part to be parsed
     * @return list of games entities from database which fulfil query
     */
    List<GameEntity> getGamesFromQuery(final SyntaxPart syntaxPart);
    
}
