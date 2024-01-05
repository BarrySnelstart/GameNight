package nl.novi.gamenight.Controller;
import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Model.Game.Game;
import nl.novi.gamenight.Services.GameService;
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
    @GetMapping
    List <GameOutputDto> getAllGames () {
        return gameService.getAllGames();
    }


    @PostMapping
    public ResponseEntity <Object> addGame(@Validated @RequestBody GameInputDto game, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map <String, String> errors = new HashMap <>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        } else {
            return ResponseEntity.created(null).body(gameService.addGame(game));
        }
    }
}
