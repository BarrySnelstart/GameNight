package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Model.Expansion;
import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Repository.ExpansionRepository;
import nl.novi.gamenight.Repository.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExpansionService {
    GameRepository gameRepository;
    ExpansionRepository expansionRepository;

    public ExpansionService(GameRepository gameRepository, ExpansionRepository expansionRepository) {
        this.gameRepository = gameRepository;
        this.expansionRepository = expansionRepository;
    }


    public ResponseEntity<Object> addGameExpansion(@Validated GameInputDto gameInput, BindingResult bindingResult, Long gameID) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        } else {
            var mainGame = gameRepository.getReferenceById(gameID);
            Expansion expansion = new Expansion();
            expansion.setGames(mainGame);
            expansionRepository.save(expansion);

            Game expansionDetails = ToEntity(gameInput);

            gameRepository.save(expansionDetails);

            /*TODO should return gameID*/
            return ResponseEntity.created(null).body(expansionDetails);
        }
    }

    public Game ToEntity(GameInputDto gameInput) {
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

    public GameOutputDto toDto(Game game) {
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
}
