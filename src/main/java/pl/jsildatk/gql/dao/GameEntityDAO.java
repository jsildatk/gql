package pl.jsildatk.gql.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import pl.jsildatk.gql.entity.GameEntity;

import java.util.List;

/**
 * Interface for database operation on GameEntity
 */
public interface GameEntityDAO {
    
    /**
     * Get list of entities from database by criteria
     *
     * @param criteria database criteria
     * @return list of GameEntity objects
     * @since 1.0.0
     */
    List<GameEntity> getByQuery(final Criteria criteria);
    
}
