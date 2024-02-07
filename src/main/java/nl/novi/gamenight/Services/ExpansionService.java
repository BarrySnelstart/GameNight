package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.Game.GameExpansionOutputDto;
import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Dto.User.UserInputDto;
import nl.novi.gamenight.Dto.User.UserOutputDto;
import nl.novi.gamenight.Model.Expansion;
import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Model.User;
import nl.novi.gamenight.Repository.ExpansionRepository;
import nl.novi.gamenight.Repository.GameRepository;
import nl.novi.gamenight.exceptions.IdNotFoundException;
import nl.novi.gamenight.security.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<GameExpansionOutputDto> getAllExpansions() {
        System.out.println();
        List<GameExpansionOutputDto> allExpansionsList = new ArrayList<>();
        for (Expansion expansion : expansionRepository.findAll()) {
            allExpansionsList.add(toDto(expansion));
        }
        return allExpansionsList;
    }

    /*TODO Admin And owning user*/
    public ResponseEntity<GameExpansionOutputDto> getExpansionsByID(Long expansionId) {

        GameExpansionOutputDto expansion =toDto( expansionRepository.getReferenceById(expansionId));


        //UserOutputDto user = toDto(expansionRepository.getReferenceById(gameId));
        return ResponseEntity.ok().body(expansion);
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


    public GameExpansionOutputDto toDto(Expansion GameX) {
        GameExpansionOutputDto gameOutputDto = new GameExpansionOutputDto();
        gameOutputDto.gameID = GameX.getExpansionID();
            return gameOutputDto;
    }




}
