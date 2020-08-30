package pl.jsildatk.gql.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("game")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {
    
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    
    @Indexed(unique = true)
    private String title;
    
    private String publisher;
    
    private String developer;
    
    private String genre;
    
    private String year;
    
}
