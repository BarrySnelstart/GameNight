package nl.novi.gamenight.Controller;


import nl.novi.gamenight.Services.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("expansion")
public class ExpansionController {
    private final GameService gameService;

    public ExpansionController(GameService gameService) {
        this.gameService = gameService;
    }


}
