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
@RequestMapping("games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    /*TODO Check Return type for correct Status*/
    @GetMapping
    public List<GameOutputDto> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<GameOutputDto> getGameByID(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(gameService.getGameByID(id));
    }

    @DeleteMapping("/game/{id}")
    public ResponseEntity deleteGameByID(@PathVariable("id") Long id) {
        return gameService.deleteGameByID(id);
    }

    @PostMapping
    public ResponseEntity<Object> addGame(@Validated @RequestBody GameInputDto gameInputDto, BindingResult bindingResult) {
        return gameService.addGame(gameInputDto, bindingResult);
    }

    @PutMapping("/game/{id}")
    public ResponseEntity<Object> updateGameByID(@PathVariable("id") Long id, @Validated @RequestBody GameInputDto updatedGame, BindingResult bindingResult) {
        return gameService.updateGameByID(id, updatedGame, bindingResult);
    }
}
