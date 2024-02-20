package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Services.GameService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public List<GameOutputDto> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{gameID}")
    public ResponseEntity<GameOutputDto> getGameByID(@PathVariable("gameID") Long gameID) {
        return ResponseEntity.ok().body(gameService.getGameByID(gameID));
    }

    @DeleteMapping("/delete/{gameID}")
    public ResponseEntity deleteGameByID(@PathVariable("gameID") Long gameID) {
        return gameService.deleteGameByID(gameID);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> addGame(@Validated @RequestBody GameInputDto gameInputDto, BindingResult bindingResult) {
        return gameService.addGame(gameInputDto, bindingResult);
    }

    /*TODO for now admin only, but owning user should be able to update his own game*/
    @PutMapping("/update/{gameID}")
    public ResponseEntity<Object> updateGameByID(@PathVariable("gameID") Long gameID, @Validated @RequestBody GameInputDto updatedGame, BindingResult bindingResult) {
        return gameService.updateGameByID(gameID, updatedGame, bindingResult);
    }
}
