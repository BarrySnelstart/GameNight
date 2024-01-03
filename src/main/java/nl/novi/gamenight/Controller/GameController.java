package nl.novi.gamenight.Controller;
import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("games")
public class GameController {
private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Object> addGame (@Validated @RequestBody GameInputDto game, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map <String, String> errors = new HashMap <>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        else
        {
            gameService.addGame(game);
            return ResponseEntity.created(null).body(gameService.addGame(game));
        }
    }
//    @GetMapping
//    public ResponseEntity <List <Game>> getAllGames() {
//        return ResponseEntity.ok(gameRepository.findAll());
//    }

    //    // Onderstaande 2 methodes zijn endpoints om andere entiteiten toe te voegen aan de Television.
//    // Dit is één manier om dit te doen, met één PathVariable en één RequestBody.
//    @PutMapping("/televisions/{id}/remotecontroller")
//    public ResponseEntity<Object> assignRemoteControllerToTelevision(@PathVariable("id") Long id,@Valid @RequestBody IdInputDto input) {
//        televisionService.assignRemoteControllerToTelevision(id, input.id);
//        return ResponseEntity.noContent().build();
//    }
}
