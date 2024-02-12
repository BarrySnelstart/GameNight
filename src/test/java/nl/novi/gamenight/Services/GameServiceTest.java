package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Model.Category;


import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



class GameServiceTest {
    private GameInputDto game1;
    private GameInputDto game2;
    private GameOutputDto outGame1;
    private GameOutputDto outGame2;

    @Mock
    GameService gameService;
@Mock
    GameRepository gameRepository;
    @BeforeEach
   public void setUp() {
        game1 = new GameInputDto("De Kolonisten van Catan: Het grote Kanaal", "999 games", 12, 2, 5, 30, 90, Category.BORD, "Gezeldschap Spel");
        game2 = new GameInputDto("Fiets hem er in", "TROS", 1, 99, 16, 30, 90, Category.OTHER, "Buiten spel");

        outGame1 = new GameOutputDto(110L, "De Kolonisten van Catan: Het grote Kanaal", "999 games", 12, 2, 5, 30, 90, Category.BORD, "Gezeldschap Spel", 5);
        outGame2 = new GameOutputDto(111L, "Kathedralen & Herbergen Uitbreiding Bordspel", "999 games", 12, 2, 4, 25, 80, Category.BORD, "Gezeldschap Spel", 4);

        gameRepository = mock(GameRepository.class);
        gameService = new GameService(gameRepository);

    }


    @Test
    void addGameFailedBindingResultHasErrors() {

        // Arrange
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        FieldError fieldError = new FieldError("gameInput", "field", "error message");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("field", "error message");

        // Act
        ResponseEntity<Object> response = gameService.addGame(game1, bindingResult);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrors, response.getBody());
    }

    @Test
    void addGame() {
        // Arrange
        BindingResult bindingResult = mock(BindingResult.class);
        // ACT
        when(bindingResult.hasErrors()).thenReturn(false);
        Map<String, String> expectedErrors = new HashMap<>();
        ResponseEntity<Object> response = gameService.addGame(game2, bindingResult);
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    @Disabled
    void getAllGames() {
        // Arrange
        List<Game> games = new ArrayList<>();
        games.add(gameService.ToEntity(game1));
        games.add(gameService.ToEntity(game2));
          when(gameRepository.findAll()).thenReturn(games);

        // Act
        List<GameOutputDto> allGames = gameService.getAllGames();

        // Assert
        assertEquals(games.size(), allGames.size());
        assertEquals(2, games.size());

    }

    @Test
    @Disabled
    void getGameByID() {
    }

    @Test
    @Disabled
    void deleteGameByID() {
    }

    @Test
    @Disabled
    void fromGameInputDtoToEntity() {
    }

    @Test
    @Disabled
    void fromEntityToGameOutputDto() {
    }

    @Test
    @Disabled
    void updateGameByID() {
    }
}