package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.expansionDto.GameExpansionInPutDto;
import nl.novi.gamenight.Dto.expansionDto.GameExpansionOutputDto;
import nl.novi.gamenight.Model.Category;
import nl.novi.gamenight.Model.Expansion;
import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Repository.ExpansionRepository;
import nl.novi.gamenight.Repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    @Disabled
    void updateGameExpansion() {
        // Arrange



        when(expansionRepository.findById(expansionID)).thenReturn(Optional.of(expansion1));

        // Act
        ResponseEntity<Object> response = expansionService.updateGameExpansion(expansionID, game2);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(expansionRepository, times(1)).findById(expansionID);
        verify(expansionRepository, times(1)).save(expansion1);
        verify(gameRepository, times(1)).save(any());

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
    @Disabled
    void getAllExpansions() {

        Expansion expansion1 = new Expansion();
        expansion1.setExpansionID(1L);
        Expansion expansion2 = new Expansion();
        expansion2.setExpansionID(2L);
        List<Expansion> expansions = new ArrayList<>();
        expansions.add(expansion1);
        expansions.add(expansion2);
        when(expansionRepository.findAll()).thenReturn(expansions);

        // Mocking the behavior of gameRepository.getReferenceById(...)
        Game baseGame1 = new Game();
        Game baseGame2 = new Game();
        when(gameRepository.getReferenceById(1L)).thenReturn(baseGame1);
        when(gameRepository.getReferenceById(2L)).thenReturn(baseGame2);

        // Call the method being tested
        List<GameExpansionOutputDto> result = expansionService.getAllExpansions();

        // Verify that expansionRepository.findAll() is called once
        verify(expansionRepository, times(1)).findAll();

        // Verify that gameRepository.getReferenceById(...) is called twice, once for each expansion
        verify(gameRepository, times(1)).getReferenceById(1L);
        verify(gameRepository, times(1)).getReferenceById(2L);

        // Verify that the correct DTOs are added to the result list
        assertEquals(2, result.size());
        // Assert other details as needed based on your implementation

        // Additional assertions can be added based on the expected behavior
    }

    @Test
    @Disabled
    void getExpansionsByID() {


        when(expansionRepository.findById(expansionID)).thenReturn(Optional.of(expansion1));



        when(gameRepository.getReferenceById(anyLong())).thenReturn(game);


        ResponseEntity<Object> responseEntity = expansionService.getExpansionsByID(expansionID);


        verify(expansionRepository, times(1)).findById(expansionID);
        verify(gameRepository, times(1)).getReferenceById(anyLong());


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void toGameEntity() {
        // Prepare input DTO
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

        // Call the method being tested
        Game game = gameService.ToEntity(gameInput);

        // Verify the properties of the returned Game entity
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


    @Test
    void toDto() {
    }
}