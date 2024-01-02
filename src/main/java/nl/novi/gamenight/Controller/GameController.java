package nl.novi.gamenight.Controller;
import nl.novi.gamenight.Model.Game.Game;
import nl.novi.gamenight.Repository.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {
private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    @PostMapping
    public ResponseEntity addGame (@RequestBody Game input) {
        gameRepository.save(input);
        return ResponseEntity.ok("Data Added" +input);
    }
    @GetMapping
    public ResponseEntity <List <Game>> getAllGames() {
        return ResponseEntity.ok(gameRepository.findAll());
    }

    //    // Onderstaande 2 methodes zijn endpoints om andere entiteiten toe te voegen aan de Television.
//    // Dit is één manier om dit te doen, met één PathVariable en één RequestBody.
//    @PutMapping("/televisions/{id}/remotecontroller")
//    public ResponseEntity<Object> assignRemoteControllerToTelevision(@PathVariable("id") Long id,@Valid @RequestBody IdInputDto input) {
//        televisionService.assignRemoteControllerToTelevision(id, input.id);
//        return ResponseEntity.noContent().build();
//    }
}
