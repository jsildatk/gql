package pl.jsildatk.gql.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import pl.jsildatk.gql.entity.GameEntity;
import pl.jsildatk.gql.service.QueryService;
import pl.jsildatk.gql.syntax.QueryException;
import pl.jsildatk.gql.syntax.SyntaxPart;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.jsildatk.gql.service.QueryService.FIND_MAPPING;

public class QueryRestControllerTest extends RestControllerAbstractTest {
    
    @MockBean
    private QueryService queryService;
    
    @Test
    public void testResponseWithExceptionWhileParsing() throws Exception {
        final String request = objectMapper.writeValueAsString(new SyntaxPart("asd", "asd", "asd"));
        final String message = "Operator: asd is not supported";
        when(queryService.getGamesFromQuery(any())).thenThrow(new QueryException(message));
        
        mockMvc.perform(post(FIND_MAPPING).contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.reason", is(message)))
                .andExpect(redirectedUrl(null))
                .andExpect(forwardedUrl(null))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testResponseWithSuccessfulParsing() throws Exception {
        final String request = objectMapper.writeValueAsString(new SyntaxPart("asd", "asd", "asd"));
        when(queryService.getGamesFromQuery(any())).thenReturn(Lists.newArrayList(new GameEntity()));
        
        mockMvc.perform(post(FIND_MAPPING).contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(redirectedUrl(null))
                .andExpect(forwardedUrl(null))
                .andExpect(status().isOk());
    }
    
}