package pl.jsildatk.gql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jsildatk.gql.dao.GameEntityDAO;
import pl.jsildatk.gql.dto.QueryRequest;
import pl.jsildatk.gql.entity.GameEntity;
import pl.jsildatk.gql.parser.QueryParser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryService {
    
    private final QueryParser parser;
    
    private final GameEntityDAO gameEntityDAO;
    
    @Override
    public List<GameEntity> getGamesFromQuery(final QueryRequest query) {
        return gameEntityDAO.getByQuery(parser.parse(query));
    }
    
}
