package nl.novi.gamenight.Model.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity (name = "game_expansion")
public class GameExpansion {
    @Id
    @GeneratedValue
    private int id;
    private String expansionName;
    private Date releaseDate;


}
