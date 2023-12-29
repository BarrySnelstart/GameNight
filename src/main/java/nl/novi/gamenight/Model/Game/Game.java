package nl.novi.gamenight.Model.Game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nl.novi.gamenight.Model.Category;
import nl.novi.gamenight.Model.GameExpansion.GameExpansion;

import java.util.List;

@Getter
@Setter

@Entity (name = "games")
public class Game {
    @Id
    @GeneratedValue
    private Long gameid;

    private String name;
    private String manufacturer;
    private int minimumPlayers;
    private int maximumPlayers;
    private int age;
    private int minimumDuration;
    private int averageDuration;
    private Category category;
    private String type;
    private String averageStarValue;
    private Long mainGameid;
    @OneToMany(mappedBy = "game")
    List<GameExpansion> gameExpansionList;
}
