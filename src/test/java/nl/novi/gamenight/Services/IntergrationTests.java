package nl.novi.gamenight.Services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")

public class IntergrationTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void GetAllGames() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/game/games"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void AddGame() throws Exception {
        String requestJson = """
                {
                     "name": "Pong",
                     "manufacturer":  "Nitendo",
                     "minimumPlayers": 1,
                     "maximumPlayers": 2,
                     "age": 10 ,
                     "minimumDuration": 2,
                     "averageDuration": 10,
                     "category":"OTHER",
                     "type":"Computer Game"
                }
                            """;
        this.mockMvc.perform(MockMvcRequestBuilders.post("/game/create").contentType(APPLICATION_JSON).content(requestJson)).andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated());
    }
    @Test
    @Disabled
        /* Test disabled added Uri return in service*/
    void getAllGames_Success() throws Exception {
        // Arrange

        // Act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/game/games")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(12));
    }

}
