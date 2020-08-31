package pl.jsildatk.gql.dao;

import pl.jsildatk.gql.dto.QueryDTO;
import pl.jsildatk.gql.entity.GameEntity;

import java.util.List;

/**
 * Interface for database operation on GameEntity
 */
public interface GameEntityDAO {
    
    /**
     * Get list of entities from database by criteria
     *
     * @param queryDTO dto containing criteria and sort
     * @return list of GameEntity objects
     * @since 1.0.0
     */
    List<GameEntity> getByQuery(final QueryDTO queryDTO);
    
}
