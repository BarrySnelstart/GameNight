package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Dto.game.GameInputDto;
import nl.novi.gamenight.Dto.game.GameOutputDto;
import nl.novi.gamenight.Services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update/{gameID}")
    public ResponseEntity<Object> updateGameByID(@PathVariable("gameID") Long gameID, @Validated @RequestBody GameInputDto updatedGame, BindingResult bindingResult) {
        return gameService.updateGameByID(gameID, updatedGame, bindingResult);
    }
}
