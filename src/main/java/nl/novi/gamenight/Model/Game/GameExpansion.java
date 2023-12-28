package nl.novi.gamenight.Model.Game;

import jakarta.persistence.*;
import nl.novi.gamenight.Model.Game.Game;

import java.util.Date;
import java.util.List;

@Entity (name = "game_expansion")
public class GameExpansion {
    @Id
    @GeneratedValue
    private Long id;
    private String expansionName;
    private Date releaseDate;

    @ManyToOne
    Game game;
}
