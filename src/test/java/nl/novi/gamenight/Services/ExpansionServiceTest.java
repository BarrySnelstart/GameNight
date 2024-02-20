package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.game.GameInputDto;
import nl.novi.gamenight.Dto.expansionDto.GameExpansionInPutDto;
import nl.novi.gamenight.Dto.expansionDto.GameExpansionOutputDto;
import nl.novi.gamenight.Model.Category;
import nl.novi.gamenight.Model.Expansion;
import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Repository.ExpansionRepository;
import nl.novi.gamenight.Repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpansionServiceTest {
    Long expansionID;
    private GameInputDto game1;
    private GameExpansionInPutDto game2;
    private Expansion expansion1;

    private Game game;
    private GameService gameService;
    @Mock
    private GameRepository gameRepository;

    @Mock
    private ExpansionRepository expansionRepository;

    @InjectMocks
    private ExpansionService expansionService;
    @BeforeEach
    void setUp (){
        Long expansionID = 110L;
        game1 = new GameInputDto("De Kolonisten van Catan: Het grote Kanaal", "999 games", 12, 2, 5, 30, 90, Category.BORD, "Gezeldschap Spel");
        game2 = new GameExpansionInPutDto(110L, "Fiets hem er in", "TROS", 1, 4,99,8, 16,Category.OTHER, "BuitenSpel" ,4,101L);
        game = new Game(111L,"Bingo", "Jumbo games", 12,2, 5, 30, 90, Category.BORD, "Gezeldschap Spel",4);
        expansion1 = new Expansion(1L,game2);
        expansion1.setGames(game);
        gameRepository = mock(GameRepository.class);
        gameService = new GameService(gameRepository);

        expansionService = new ExpansionService(gameRepository, expansionRepository);
    }
    @Test
    void addGameExpansion() {
        // Arrange

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        Long gameID = 1L;

        // Act
        ResponseEntity<Object> response = expansionService.addGameExpansion(game1, bindingResult, gameID);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    @Test
    void addGameExpansionValidationErrors() {
        // Arrange

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        Long gameID = 1L;

        // Act
        ResponseEntity<Object> response = expansionService.addGameExpansion(game1, bindingResult, gameID);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void deleteAGameExpansionByID() {
        // Arrange
        when(expansionRepository.findById(expansionID)).thenReturn(Optional.of(new Expansion()));

        // ACT
        ResponseEntity responseEntity = expansionService.deleteAGameExpansionByID(expansionID);

        verify(expansionRepository, times(1)).deleteById(expansionID);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Expansion Deleted", responseEntity.getBody());
    }
    @Test
    void FailedDeleteAGameExpansionByID() {
        // Arrange

        // ACT
        when(expansionRepository.findById(expansionID)).thenReturn(Optional.empty());
        ResponseEntity responseEntity = expansionService.deleteAGameExpansionByID(expansionID);
        //verify(expansionRepository, never()).deleteById(anyLong());

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("ID not found in database", responseEntity.getBody());

    }
    @Test
    void getAllExpansions() {
        List<Expansion> expansions = new ArrayList<>();
        Game test = new Game(111L,"Bingo", "Jumbo games", 12,2, 5, 30, 90, Category.BORD, "Gezeldschap Spel",4);
        Expansion expansion1 = new Expansion();
        expansion1.setGames(test);
        expansions.add(expansion1);

        List<GameExpansionOutputDto> expansionsOutput = new ArrayList<>();


        when(expansionRepository.findAll()).thenReturn(expansions);
        when(gameRepository.getReferenceById(expansion1.getExpansionID())).thenReturn(game);
        when(gameRepository.getReferenceById(expansion1.getGames().getGameID())).thenReturn(game);

        for(Expansion expansion : expansionRepository.findAll()) {
            var gameData = gameRepository.getReferenceById(expansion.getExpansionID());
            var baseGameData = gameRepository.getReferenceById(expansion.getGames().getGameID());
            expansionsOutput.add(expansionService.toDto(expansion, gameData, baseGameData));
        }

        assertEquals(expansionsOutput.size(), 1);
    }
    @Test
    void getExpansionsByID() {
        when(expansionRepository.findById(expansion1.getExpansionID())).thenReturn(Optional.of(expansion1));
        when(expansionRepository.getReferenceById(expansion1.getExpansionID())).thenReturn(expansion1);
        when(gameRepository.getReferenceById(expansion1.getExpansionID())).thenReturn(game);
        when(gameRepository.getReferenceById(expansion1.getGames().getGameID())).thenReturn(game);

        ResponseEntity<?> result = expansionService.getExpansionsByID(expansion1.getExpansionID());

        assertTrue(result.getStatusCode().is2xxSuccessful());
    }
    @Test
    void toGameEntity() {
        GameInputDto gameInput = new GameInputDto();
        gameInput.name = "Test Game";
        gameInput.manufacturer = "Test Manufacturer";
        gameInput.minimumPlayers = 2;
        gameInput.maximumPlayers = 4;
        gameInput.age = 10;
        gameInput.minimumDuration = 30;
        gameInput.averageDuration = 60;
        gameInput.category = Category.BORD;
        gameInput.type = "Strategy";

        Game game = gameService.ToEntity(gameInput);

        assertEquals(gameInput.name, game.getName());
        assertEquals(gameInput.manufacturer, game.getManufacturer());
        assertEquals(gameInput.minimumPlayers, game.getMinimumPlayers());
        assertEquals(gameInput.maximumPlayers, game.getMaximumPlayers());
        assertEquals(gameInput.age, game.getAge());
        assertEquals(gameInput.minimumDuration, game.getMinimumDuration());
        assertEquals(gameInput.averageDuration, game.getAverageDuration());
        assertEquals(gameInput.category, game.getCategory());
        assertEquals(gameInput.type, game.getType());
    }
}