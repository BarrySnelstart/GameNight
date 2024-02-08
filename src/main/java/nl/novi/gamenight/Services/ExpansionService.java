package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.expansionDto.GameExpansionOutputDto;
import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Model.Expansion;
import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Repository.ExpansionRepository;
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

            /*TODO should return BasegameID See outputDto*/
            return ResponseEntity.created(null).body(expansionDetails);
        }
    }


    public ResponseEntity deleteAGameExpansionByID(Long expansionID) {
        Optional<Expansion> ifExist = expansionRepository.findById(expansionID);
        if (ifExist.isPresent()) {
            expansionRepository.deleteById(expansionID);
            return ResponseEntity.ok("Expansion Deleted");
        } else {
            return new ResponseEntity<>(new IdNotFoundException("ID not found in database").getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    /*TODO Admin Only*/
//    public List<GameExpansionOutputDto> getAllExpansions() {
//        System.out.println();
//        List<GameExpansionOutputDto> allExpansionsList = new ArrayList<>();
//        for (Expansion expansion : expansionRepository.findAll()) {
//            allExpansionsList.add(toDto(expansion));
//        }
//        return allExpansionsList;
//    }

    /*TODO Admin And owning user*/
    /*TODO Error handling*/
    public ResponseEntity<Object> getExpansionsByID(Long expansionId) {

        Optional<Expansion> ifExist = expansionRepository.findById(expansionId);
        if (ifExist.isPresent()) {
            var expansionData = expansionRepository.getReferenceById(expansionId);
            var gameData = gameRepository.getReferenceById(expansionData.getExpansionID());
            var baseGameData = gameRepository.getReferenceById(expansionData.getGames().getGameID());

            return ResponseEntity.ok().body(toDto(expansionData, gameData, baseGameData));
        } else {
            return new ResponseEntity<>(new IdNotFoundException("ID not found in database").getMessage(), HttpStatus.BAD_REQUEST);
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


    public GameExpansionOutputDto toDto(Expansion expansionData ,Game game, Game baseGame) {
        GameExpansionOutputDto gameOutputDto = new GameExpansionOutputDto();
        gameOutputDto.gameID = expansionData.getExpansionID();
        gameOutputDto.baseGameID = expansionData.getGames().getGameID();
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
        gameOutputDto.baseGameName = baseGame.getName();
        return gameOutputDto;
    }




}
