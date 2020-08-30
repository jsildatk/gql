package pl.jsildatk.gql;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.jsildatk.gql.entity.GameEntity;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class GameDTO {
    
    private final String title;
    
    private final String publisher;
    
    private final String developer;
    
    private final String genre;
    
    private final String year;
    
    public GameEntity toEntity() {
        return GameEntity.builder()
                .title(title)
                .publisher(publisher)
                .developer(developer)
                .genre(genre)
                .year(year)
                .build();
    }
    
    public static GameDTO fromEntity(GameEntity gameEntity) {
        return new GameDTO(gameEntity.getTitle(), gameEntity.getPublisher(), gameEntity.getDeveloper(), gameEntity.getGenre(), gameEntity.getYear());
    }
    
}
