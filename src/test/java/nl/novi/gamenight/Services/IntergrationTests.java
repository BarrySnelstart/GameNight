package nl.novi.gamenight.Services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;

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
                .andExpect(MockMvcResultMatchers.status().isOk())
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
        this.mockMvc.perform(MockMvcRequestBuilders.post("/game/create").contentType(APPLICATION_JSON).content(requestJson)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
