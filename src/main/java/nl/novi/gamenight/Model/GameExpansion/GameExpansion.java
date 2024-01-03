package nl.novi.gamenight.Model.GameExpansion;

import jakarta.persistence.*;
import nl.novi.gamenight.Model.Game.Game;

import java.util.Date;
import java.util.List;

@Entity (name = "game_expansion")
public class GameExpansion {
    @Id
    @GeneratedValue
    private Long gameExpansionID;
    @ManyToOne
    Game game;
}
