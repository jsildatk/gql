package pl.jsildatk.gql.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.jsildatk.gql.entity.GameEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameEntityDAOImpl implements GameEntityDAO {
    
    private final MongoTemplate mongoTemplate;
    
    @Override
    public List<GameEntity> getByQuery(final Criteria criteria) {
        final Query query = new Query(criteria);
        return mongoTemplate.find(query, GameEntity.class);
    }
    
}
