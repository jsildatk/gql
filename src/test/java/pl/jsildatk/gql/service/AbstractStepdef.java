package pl.jsildatk.gql.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jsildatk.gql.GameDTO;
import pl.jsildatk.gql.GqlApplication;
import pl.jsildatk.gql.dao.GameEntityDAO;
import pl.jsildatk.gql.entity.GameEntity;
import pl.jsildatk.gql.parser.QueryParser;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = GqlApplication.class, loader = SpringBootContextLoader.class)
@TestPropertySource(locations = "/application.properties")
public abstract class AbstractStepdef {
    
    @Autowired
    protected MongoTemplate mongoTemplate;
    
    @Autowired
    protected QueryParser queryParser;
    
    @Autowired
    protected GameEntityDAO gameEntityDAO;
    
    protected QueryService queryService;
    
    protected String operator;
    
    protected String field;
    
    protected String value;
    
    protected List<GameDTO> result;
    
    protected void setUp() {
        queryService = new QueryServiceImpl(queryParser, gameEntityDAO);
        result = new ArrayList<>();
    }
    
    protected void loadData(List<GameDTO> games) {
        mongoTemplate.findAllAndRemove(new Query(), GameEntity.class);
        for ( GameDTO gameDTO : games ) {
            mongoTemplate.save(gameDTO.toEntity());
        }
    }
    
    protected List<GameDTO> convertToDTO(List<GameEntity> games) {
        List<GameDTO> result = new ArrayList<>();
        for ( GameEntity gameEntity : games ) {
            result.add(GameDTO.fromEntity(gameEntity));
        }
        return result;
    }
    
}
