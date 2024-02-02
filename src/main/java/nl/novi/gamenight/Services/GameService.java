package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Repository.GameRepository;
import nl.novi.gamenight.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public ResponseEntity<Object> addGame(@Validated GameInputDto gameInput, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        } else {
            Game game = fromGameInputDtoToEntity(gameInput);
            gameRepository.save(game);
            return ResponseEntity.created(null).body(game);
        }
    }

    public List<GameOutputDto> getAllGames() {
        List<GameOutputDto> allGamesList = new ArrayList<>();
        for (Game games : gameRepository.findAll()) {
            allGamesList.add(fromEntityToGameOutputDto(games));
        }
        return allGamesList;
    }

    /*TODO Exception handler for id not found*/
    public GameOutputDto getGameByID(Long id) {

        var game = gameRepository.getReferenceById(id);
        return fromEntityToGameOutputDto(game);
    }

    public ResponseEntity deleteGameByID(Long id) {
        Optional<Game> ifExist = gameRepository.findById(id);
        if (ifExist.isPresent()) {
            gameRepository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        } else {
            return new ResponseEntity<>(new IdNotFoundException("GameID not found in database").getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    public Game fromGameInputDtoToEntity(GameInputDto gameInput) {
        var game = new Game();
        game.setName(gameInput.name);
        game.setManufacturer(gameInput.manufacturer);
        game.setMinimumPlayers(gameInput.minimumPlayers);
        game.setMaximumPlayers(gameInput.maximumPlayers);
        game.setAge(gameInput.age);
        game.setMinimumDuration(gameInput.minimumDuration);
        game.setAverageDuration(gameInput.averageDuration);
        game.setCategory(gameInput.category);
        game.setType(gameInput.type);
        return game;
    }

    public GameOutputDto fromEntityToGameOutputDto(Game game) {
        GameOutputDto gameOutputDto = new GameOutputDto();
        gameOutputDto.gameID = game.getGameID();
        gameOutputDto.name = game.getName();
        gameOutputDto.manufacturer = game.getManufacturer();
        gameOutputDto.minimumPlayers = game.getMinimumDuration();
        gameOutputDto.maximumPlayers = game.getMaximumPlayers();
        gameOutputDto.age = game.getAge();
        gameOutputDto.minimumDuration = game.getMinimumDuration();
        gameOutputDto.averageDuration = game.getAverageDuration();
        gameOutputDto.category = game.getCategory();
        gameOutputDto.type = game.getType();
        gameOutputDto.averageStarValue = game.getAverageStarValue();
        return gameOutputDto;
    }

    public ResponseEntity updateGameByID(@Validated Long id, GameInputDto updatedGame, BindingResult bindingResult) {
        Optional<Game> ifExist = gameRepository.findById(id);
        if (ifExist.isPresent()) {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(errors);
            } else {
                var gameToUpdate = gameRepository.getReferenceById(id);
                gameToUpdate.setName(updatedGame.name);
                gameToUpdate.setManufacturer(updatedGame.manufacturer);
                gameToUpdate.setMinimumPlayers(updatedGame.minimumPlayers);
                gameToUpdate.setMaximumPlayers(updatedGame.maximumPlayers);
                gameToUpdate.setAge(updatedGame.age);
                gameToUpdate.setMinimumDuration(updatedGame.minimumDuration);
                gameToUpdate.setAverageDuration(updatedGame.averageDuration);
                gameToUpdate.setCategory(updatedGame.category);
                gameToUpdate.setType(updatedGame.type);
                gameRepository.save(gameToUpdate);
                return ResponseEntity.created(null).body(updatedGame);
            }
        } else {
            return new ResponseEntity<>(new IdNotFoundException("ID not found in database").getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}



