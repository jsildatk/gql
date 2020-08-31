package pl.jsildatk.gql.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.jsildatk.gql.dto.QueryDTO;
import pl.jsildatk.gql.entity.GameEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameEntityDAOImpl implements GameEntityDAO {
    
    private final MongoTemplate mongoTemplate;
    
    @Override
    public List<GameEntity> getByQuery(final QueryDTO queryDTO) {
        final Sort sort = queryDTO.getSort();
        final Query query = new Query(queryDTO.getCriteria());
        if ( sort != null ) {
            query.with(sort);
        }
        return mongoTemplate.find(query, GameEntity.class);
    }
    
}
