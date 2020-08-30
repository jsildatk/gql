package pl.jsildatk.gql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jsildatk.gql.dao.GameEntityDAO;
import pl.jsildatk.gql.entity.GameEntity;
import pl.jsildatk.gql.parser.QueryParser;
import pl.jsildatk.gql.syntax.SyntaxPart;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryService {
    
    private final QueryParser parser;
    
    private final GameEntityDAO gameEntityDAO;
    
    @Override
    public List<GameEntity> getGamesFromQuery(final SyntaxPart syntaxPart) {
        return gameEntityDAO.getByQuery(parser.parse(syntaxPart));
    }
    
}
